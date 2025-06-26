package com.ecommerce.services.auth;

import com.ecommerce.dto.JwtResponse;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.User.UserDTO;
import com.ecommerce.entities.Role;
import com.ecommerce.entities.User;
import com.ecommerce.mappers.UserProfileMapper;
import com.ecommerce.repositories.UserRepository;
import com.ecommerce.services.TokenBlackListService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jWTService;
    private final PasswordEncoder passwordEncoder;
    private final TokenBlackListService tokenBlackListService;
    private final JwtService jwtService;

    public JwtResponse login(LoginRequest request) throws EntityNotFoundException {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
       if(optionalUser.isEmpty()){
           throw new EntityNotFoundException("User not found");
       } else {
           UserDTO user = UserProfileMapper.toDTO(optionalUser.get());
           UserDetails userDetails = optionalUser.get();
           user.setRole(optionalUser.get().getRole().name());

           String token = jWTService.getToken(userDetails);

           return new JwtResponse(token, user);
       }

    }
    public JwtResponse register(RegisterRequest request) throws DataIntegrityViolationException {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new DataIntegrityViolationException("Este email ya existe");
        }
        User user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .birthDate(request.getBirthDate())
                .role(Role.USER)
                .build();
       User userCreate = userRepository.save(user);

        UserDTO userDTO = UserProfileMapper.toDTO(userCreate);
        userDTO.setRole(user.getRole().name());


        String token = jWTService.getToken(userCreate);

        return JwtResponse.builder()
                .token(token)
                .user(userDTO)
                .build();
    }
    public void  logout(String authHeader){
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadCredentialsException("Invalid token or null");
        }
        String token = authHeader.substring(7);

        LocalDateTime expiresAt = jwtService.extractExpiration(token);
        tokenBlackListService.blacklistToken(token, expiresAt);
        System.out.println("Logout successful");
    }
}

package com.ecommerce.services.auth;

import com.ecommerce.dto.JwtResponse;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.dto.User.UserDTO;
import com.ecommerce.entities.Role;
import com.ecommerce.entities.User;
import com.ecommerce.mappers.UserMapper;
import com.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jWTService;
    private final PasswordEncoder passwordEncoder;

    public JwtResponse login(LoginRequest request){
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
       if(optionalUser.isEmpty()){
           throw new RuntimeException("User not found");
       } else {
           UserDTO user = UserMapper.toDTO(optionalUser.get());
           UserDetails userDetails = optionalUser.get();
           user.setRole(optionalUser.get().getRole());

           String token = jWTService.getToken(userDetails);

           return new JwtResponse(token, user);
       }

    }
    public JwtResponse register(RegisterRequest request){
        User user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .birthDate(request.getBirthDate())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        UserDTO userDTO = UserMapper.toDTO(user);
        userDTO.setRole(user.getRole());


        String token = jWTService.getToken(user);

        return JwtResponse.builder()
                .token(token)
                .user(userDTO)
                .build();
    }
}

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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService; // Usar solo uno, eliminé duplicado jWTService
    private final PasswordEncoder passwordEncoder;
    private final TokenBlackListService tokenBlackListService;

    public JwtResponse login(LoginRequest request) throws EntityNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserDTO userDTO = UserProfileMapper.toDTO(user);
        userDTO.setRole(user.getRole().name());

        String accessToken = jwtService.getToken(user);
        String refreshToken = jwtService.generateRefreshToken(user); // NUEVO

        return JwtResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken) // NUEVO
                .user(userDTO)
                .build();
    }

    public JwtResponse register(RegisterRequest request) throws DataIntegrityViolationException {
        if (userRepository.existsByEmail(request.getEmail())) {
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
        User userCreated = userRepository.save(user);

        UserDTO userDTO = UserProfileMapper.toDTO(userCreated);
        userDTO.setRole(user.getRole().name());

        String accessToken = jwtService.getToken(userCreated);
        String refreshToken = jwtService.generateRefreshToken(userCreated); // NUEVO

        return JwtResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken) // NUEVO
                .user(userDTO)
                .build();
    }

    public void logout(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BadCredentialsException("Invalid token or null");
        }
        String token = authHeader.substring(7);

        LocalDateTime expiresAt = jwtService.extractExpiration(token);
        tokenBlackListService.blacklistToken(token, expiresAt);
        System.out.println("Logout successful");
    }

    public JwtResponse refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            throw new BadCredentialsException("Refresh token vacío o nulo");
        }

        String userEmail = jwtService.getEmailFromToken(refreshToken);

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new BadCredentialsException("Refresh token inválido o expirado");
        }

        String newAccessToken = jwtService.getToken(user);

        String newRefreshToken = jwtService.generateRefreshToken(user); // rotar el refresh opcional

        UserDTO userDTO = UserProfileMapper.toDTO(user);
        userDTO.setRole(user.getRole().name());

        return JwtResponse.builder()
                .token(newAccessToken)
                .refreshToken(newRefreshToken)
                .user(userDTO)
                .build();
    }

}


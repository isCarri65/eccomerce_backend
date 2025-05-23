controllers/AuthController.java,"package com.ecommerce.controllers;

import com.ecommerce.entities.User;
import com.ecommerce.entities.Role;
import com.ecommerce.entities.LoginRequest;
import com.ecommerce.entities.JwtResponse;
import com.ecommerce.repositories.UserRepository;
import com.ecommerce.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(""/auth"")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(""/login"")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(request.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
                return ResponseEntity.ok(new JwtResponse(token));
            }
        }

        return ResponseEntity.status(401).body(""Usuario o contraseña incorrectos"");
    }

    @PostMapping(""/register"")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setRole(Role.USER);
        return ResponseEntity.ok(userRepository.save(user));
    }
}
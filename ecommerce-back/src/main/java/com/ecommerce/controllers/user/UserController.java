package com.ecommerce.controllers;

import com.ecommerce.entities.User;
import com.ecommerce.entities.Role;
import com.ecommerce.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 🔒 Crear usuario (solo ADMIN puede asignar role ADMIN)
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, HttpServletRequest request) {
        String requesterRole = (String) request.getAttribute("role");

        // Solo ADMIN puede asignar rol ADMIN
        if (user.getRole() == Role.ADMIN && !"ADMIN".equals(requesterRole)) {
            user.setRole(Role.USER);
        }

        return ResponseEntity.ok(userRepository.save(user));
    }

    // 🔒 Obtener todos los usuarios (solo ADMIN)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request) {
        if (!"ADMIN".equals(request.getAttribute("role"))) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(userRepository.findAll());
    }

    // 🔒 Actualizar usuario por ID (solo ADMIN)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newData, HttpServletRequest request) {
        if (!"ADMIN".equals(request.getAttribute("role"))) {
            return ResponseEntity.status(403).build();
        }

        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newData.getUsername());
                    user.setPassword(newData.getPassword());
                    user.setRole(newData.getRole());
                    return ResponseEntity.ok(userRepository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔒 Eliminar usuario (solo ADMIN)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, HttpServletRequest request) {
        if (!"ADMIN".equals(request.getAttribute("role"))) {
            return ResponseEntity.status(403).build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

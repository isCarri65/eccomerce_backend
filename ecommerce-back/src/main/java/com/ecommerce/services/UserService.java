package com.ecommerce.services;

import com.ecommerce.entities.Role;
import com.ecommerce.entities.User;
import com.ecommerce.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long>{
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
    public User createUser(User user) {
        if (user.getRole() == Role.ADMIN) {
            var current = getCurrentUser();
            if (current == null || current.getRole() != Role.ADMIN) {
                throw new RuntimeException("Only ADMIN users can create another ADMIN.");
            }
        }
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }
        return userRepository.save(user);
    }

    private User getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;
        String email = auth.getName();
        return userRepository.findByEmail(email).orElse(null);
    }

}

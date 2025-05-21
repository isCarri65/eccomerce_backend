package com.ecommerce.services;

import com.ecommerce.entities.User;
import com.ecommerce.entities.Role;
import com.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public User createAdmin(User user, String requesterRole) {
        if (!"ADMIN".equals(requesterRole)) {
            throw new SecurityException("Solo un administrador puede crear otro admin.");
        }
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

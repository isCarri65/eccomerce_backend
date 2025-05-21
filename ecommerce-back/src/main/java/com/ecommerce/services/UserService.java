package com.ecommerce.services;

import com.ecommerce.entities.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user);
    User createAdmin(User user, String requesterRole);
    List<User> getAll();
    Optional<User> getById(Long id);
    void delete(Long id);
}

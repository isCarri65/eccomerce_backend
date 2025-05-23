import com.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
"
services/UserService.java,"package com.ecommerce.services;

        import com.ecommerce.entities.Role;
import com.ecommerce.entities.User;
import com.ecommerce.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user, HttpServletRequest request) {
        String requesterRole = (String) request.getAttribute(""role"");
        if (user.getRole() == Role.ADMIN && !""ADMIN"".equals(requesterRole)) {
            user.setRole(Role.USER);
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> updateUser(Long id, User newData) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newData.getUsername());
                    user.setPassword(newData.getPassword());
                    user.setRole(newData.getRole());
                    return userRepository.save(user);
                });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
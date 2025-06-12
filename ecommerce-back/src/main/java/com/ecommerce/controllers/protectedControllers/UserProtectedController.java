package com.ecommerce.controllers.protectedControllers;

import com.ecommerce.dto.User.UpdateUserDTO;
import com.ecommerce.dto.User.UserDTO;
import com.ecommerce.entities.User;
import com.ecommerce.mappers.UserMapper;
import com.ecommerce.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/protected/users")
public class UserProtectedController {

    private final UserService userService;

    public UserProtectedController(UserService service) {
        this.userService = service;
    }
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getMyProfile() {
        User user = userService.getCurrentUser();
        // Ya no hay riesgo de null
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateMyProfile(@RequestBody UpdateUserDTO userDTO) throws Exception {
        User user = userService.getCurrentUser();
        UserMapper.updateUserFromDTO(userDTO, user);
        User updatedUser = userService.update(user.getId(), user);
        return ResponseEntity.ok(UserMapper.toDTO(updatedUser));
    }



}

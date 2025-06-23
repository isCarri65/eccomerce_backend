package com.ecommerce.controllers.protectedControllers;

import com.ecommerce.dto.User.UpdateUserDTO;
import com.ecommerce.dto.User.UserDTO;
import com.ecommerce.entities.User;
import com.ecommerce.mappers.UserProfileMapper;
import com.ecommerce.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserProtectedController {

    private final UserService userService;

    public UserProtectedController(UserService service) {
        this.userService = service;
    }
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getMyProfile() {
        User user = userService.getCurrentUser();
        // Ya no hay riesgo de null
        return ResponseEntity.ok(UserProfileMapper.toDTO(user));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateMyProfile(@RequestBody UpdateUserDTO userDTO){
        User user = userService.getCurrentUser();
        UserProfileMapper.updateUserFromDTO(userDTO, user);
        User updatedUser = userService.update(user.getId(), user);
        return ResponseEntity.ok(UserProfileMapper.toDTO(updatedUser));
    }



}

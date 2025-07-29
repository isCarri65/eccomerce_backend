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
    private final UserProfileMapper userProfileMapper;

    public UserProtectedController(UserService service, UserProfileMapper mapper) {

        this.userService = service;
        this.userProfileMapper = mapper;
    }
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getMyProfile() {
        User user = userService.getCurrentUser();
        // Ya no hay riesgo de null
        return ResponseEntity.ok(userProfileMapper.toDTO(user));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateMyProfile(@RequestBody UpdateUserDTO userDTO){
        User user = userService.getCurrentUser();
        userProfileMapper.updateUserFromDTO(userDTO, user);
        User updatedUser = userService.update(user.getId(), user);
        return ResponseEntity.ok(userProfileMapper.toDTO(updatedUser));
    }



}

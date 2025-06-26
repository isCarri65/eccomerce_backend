package com.ecommerce.mappers;

import com.ecommerce.customException.InvalidRoleException;
import com.ecommerce.dto.User.*;
import com.ecommerce.entities.Role;
import com.ecommerce.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserAdminMapper implements BaseAdminMapper<User, Long, UserAdminDTO, CreateUserAdminDTO, UpdateUserAdminDTO> {
    PasswordEncoder passwordEncoder;
    public UserAdminDTO toDTO(User user) {
        UserAdminDTO dto = new UserAdminDTO();
        dto.setId(user.getId());
        dto.setLastName(user.getLastName());
        dto.setName(user.getName());
        dto.setBirthDate(user.getBirthDate());
        dto.setRole(String.valueOf(user.getRole()));
        dto.setEnabled(user.isEnabled());
        dto.setDeleted(user.isDeleted());
        return dto;
    }

    public User UDTOtoEntity(UpdateUserAdminDTO dto, Long userId) {
        User user = new User();
        user.setId(userId);
        user.setBirthDate(dto.getBirthDate());
        user.setLastName(dto.getLastName());
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEnabled(dto.isEnabled());
        user.setDeleted(dto.isDeleted());

        try {
            user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidRoleException("Invalid role: " + dto.getRole());
        }
        // No modificar campos sensibles como password o roles
        return user;
    }

    public User CDTOtoEntity(CreateUserAdminDTO dto) {
        User user = new User();
        user.setBirthDate(dto.getBirthDate());
        user.setLastName(dto.getLastName());
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        try {
            user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new InvalidRoleException("Invalid role: " + dto.getRole());
        }
        return user;
    }

}

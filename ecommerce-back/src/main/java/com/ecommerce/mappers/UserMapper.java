package com.ecommerce.mappers;
import com.ecommerce.dto.User.UpdateUserDTO;
import com.ecommerce.dto.User.UserDTO;
import com.ecommerce.entities.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setLastName(user.getLastName());
        dto.setName(user.getName());
        dto.setBirthDate(user.getBirthDate());
        return dto;
    }

    public static void updateUserFromDTO(UpdateUserDTO dto, User user) {
        user.setBirthDate(dto.getBirthDate());
        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
        // No modificar campos sensibles como password o roles
    }
}

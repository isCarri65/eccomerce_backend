package com.ecommerce.dto.User;

import com.ecommerce.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String phoneNumber;
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private Role role;
}

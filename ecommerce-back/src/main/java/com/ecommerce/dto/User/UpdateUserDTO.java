package com.ecommerce.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDTO {
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;
}

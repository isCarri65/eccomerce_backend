package com.ecommerce.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    private String name;
    private String secondName;
    private String email;
    private String password;
    private LocalDate birthDate;
}

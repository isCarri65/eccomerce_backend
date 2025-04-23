package com.ecommerce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String secondName;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<UserAddress> userAddresses;
}

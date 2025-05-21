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

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<UserAddress> userAddresses;
}

package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_address")
public class UserAddress {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User idUser;

    @ManyToOne
    private Address idAddress;
}

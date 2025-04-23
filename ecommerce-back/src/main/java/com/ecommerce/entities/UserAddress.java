package com.ecommerce.entities;

import jakarta.persistence.*;

@Entity
public class UserAddress {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Address address;
}

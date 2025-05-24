package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_address")
public class UserAddress {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;
}

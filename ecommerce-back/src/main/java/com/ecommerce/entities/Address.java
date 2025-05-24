package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Integer number;
    private String apartment;
    private String locality;
    private String postal;

    @ManyToMany(mappedBy = "addresses")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "address")
    private List<Order> orders;
}

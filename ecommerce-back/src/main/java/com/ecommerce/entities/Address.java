package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private Integer number;
    private String apartment;
    private String locality;
    private String postal;

    @OneToMany(mappedBy = "address")
    private List<UserAddress> userAddresses;

    @OneToMany(mappedBy = "address")
    private List<Order> orders;
}

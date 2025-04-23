package com.ecommerce.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
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
}

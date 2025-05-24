package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "discount")
public class Discount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double percentage;
    private Boolean state;

    @OneToMany(mappedBy = "discount")
    private Set<ProductDiscount> productDiscounts = new HashSet<>();

    @OneToMany(mappedBy = "discount")
    private Set<OrderDetail> orderDetails = new HashSet<>();
}

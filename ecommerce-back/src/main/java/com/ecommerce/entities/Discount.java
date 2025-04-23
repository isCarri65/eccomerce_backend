package com.ecommerce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Discount {
    @Id @GeneratedValue
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double percentage;
    private String state;

    @OneToMany(mappedBy = "discount")
    private List<ProductDiscount> productDiscounts;
}

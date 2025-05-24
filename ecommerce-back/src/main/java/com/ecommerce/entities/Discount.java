package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "discount")
public class Discount {
    @Id @GeneratedValue
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double percentage;
    private Boolean state;

    @OneToMany(mappedBy = "discount")
    private List<ProductDiscount> productDiscounts;

    @OneToMany(mappedBy = "discount")
    private List<OrderDetail> orderDetailDiscounts;
}

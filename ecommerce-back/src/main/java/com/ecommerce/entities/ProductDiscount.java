package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_discount")
public class ProductDiscount {
    @Id @GeneratedValue
    private Long id;
    private Boolean state;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Discount discount;
}

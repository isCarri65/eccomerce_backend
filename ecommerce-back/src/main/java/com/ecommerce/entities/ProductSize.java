package com.ecommerce.entities;

import jakarta.persistence.*;

@Entity
public class ProductSize {
    @Id @GeneratedValue
    private Long id;
    private String state;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Size size;
}
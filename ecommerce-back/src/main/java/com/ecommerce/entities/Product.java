package com.ecommerce.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Integer quantity;
    private Double buyPrice;
    private Double sellPrice;
    private String description;
    private String color;
    private String state;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Size size;

    @OneToMany(mappedBy = "product")
    private List<Detail> details;

    @OneToMany(mappedBy = "product")
    private List<ProductDiscount> productDiscounts;
}

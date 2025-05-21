package com.ecommerce.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductVariant {
    @Id @GeneratedValue
    private Long id;
    private Integer quantity;
    private Boolean state;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Size size;

    @ManyToOne
    private Color color;

    @OneToMany(mappedBy = "productVariant")
    private List<OrderDetail> details;

}

package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "product_variant")
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

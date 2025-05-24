package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_discount")
public class ProductDiscount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private Discount discount;
}

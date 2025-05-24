package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_gallery")
public class ProductGallery {
    @Id @GeneratedValue
    private Long id;
    private String state;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Gallery gallery;
}

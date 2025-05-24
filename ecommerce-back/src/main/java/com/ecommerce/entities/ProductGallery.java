package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_gallery")
public class ProductGallery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_gallery")
    private Gallery gallery;
}

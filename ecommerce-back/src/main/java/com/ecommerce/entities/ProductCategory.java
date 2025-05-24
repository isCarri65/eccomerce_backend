package com.ecommerce.entities;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Product idProduct;

    @ManyToOne
    private Category idCategory;
}
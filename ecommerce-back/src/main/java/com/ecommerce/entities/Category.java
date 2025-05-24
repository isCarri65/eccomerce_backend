package com.ecommerce.entities;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private Gallery idGallery;

    @OneToMany(mappedBy = "category")
    private List<ProductCategory> ProductCategories;
}

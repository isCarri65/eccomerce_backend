package com.ecommerce.entities;
import jakarta.persistence.*;

import java.awt.*;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private Gallery idGallery;

    @OneToMany(mappedBy = "category")
    private List<ProductCategory> ProductCategories;
}

package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "color")
public class Color {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "color")
    private List<ProductVariant> productsVariants;
}
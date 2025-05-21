package com.ecommerce.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Color {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "color")
    private List<ProductColor> productColor;
}
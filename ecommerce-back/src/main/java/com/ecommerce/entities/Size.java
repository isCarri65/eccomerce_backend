package com.ecommerce.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Size {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "size")
    private List<Product> products;
}

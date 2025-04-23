package com.ecommerce.entities;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    private Type type;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}

package com.ecommerce.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "type")
    private List<CategoryType> categoryTypes;
}

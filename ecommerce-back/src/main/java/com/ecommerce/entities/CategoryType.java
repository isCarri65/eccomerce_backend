package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "category_type")
public class CategoryType {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Category idCategory;

    @ManyToOne
    private Type idType;
}

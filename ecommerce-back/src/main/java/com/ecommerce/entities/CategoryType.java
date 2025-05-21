package com.ecommerce.entities;

import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class CategoryType {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Category idCategory;

    @ManyToOne
    private Type idType;
}

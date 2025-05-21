package com.ecommerce.entities;
import com.ecommerce.entities.Address;
import com.ecommerce.entities.User;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class ProductCategory {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Product idProduct;

    @ManyToOne
    private Category idCategory;
}
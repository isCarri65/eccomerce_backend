package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "gallery")
public class Gallery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;

    @OneToMany(mappedBy = "gallery")
    private Set<ProductGallery> productGalleries = new HashSet<>();
}

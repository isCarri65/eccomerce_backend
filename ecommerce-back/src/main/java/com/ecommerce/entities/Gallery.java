package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "gallery")
public class Gallery {
    @Id @GeneratedValue
    private Long id;
    private String image;

    @OneToMany(mappedBy = "gallery")
    private List<ProductGallery> productGalleries;
}

package com.ecommerce.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Gallery {
    @Id @GeneratedValue
    private Long id;
    private String image;

    @OneToMany(mappedBy = "gallery")
    private List<ProductGallery> productGalleries;
}

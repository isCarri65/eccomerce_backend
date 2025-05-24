package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Double buyPrice;
    private Double sellPrice;
    private String description;
    private Boolean state;

    @Enumerated(EnumType.STRING)
    private ProductGenreENUM genre;

    @OneToMany(mappedBy = "product")
    private List<Favorite> productFavorites;

    @OneToMany(mappedBy = "product")
    private List<ProductVariant> productVariants;

    @OneToMany(mappedBy = "product")
    private List<ProductDiscount> productDiscounts;

    @OneToMany(mappedBy = "product")
    private List<ProductCategory> productCategories;

    @OneToMany(mappedBy = "product")
    private List<ProductGallery> productGalleries;
}

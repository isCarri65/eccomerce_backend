package com.ecommerce.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Size {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private SizeTypeENUM sizeType;

    @OneToMany(mappedBy = "size")
    private List<ProductVariant> productVariantSizes;
}

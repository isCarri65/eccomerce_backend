package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "size")
public class Size {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private SizeTypeENUM sizeType;

    @OneToMany(mappedBy = "size")
    private List<ProductVariant> productVariantSizes;
}

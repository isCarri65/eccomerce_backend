package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = "productVariants")

public class Product extends Base{
    private String name;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;

    private BigDecimal finalPrice;
    private String description;
    private Boolean state;

    private Integer salesCount = 0;

    @Column
    private Double recommendedScore;

    @Enumerated(EnumType.STRING)
    private ProductGenreENUM genre;

    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_category"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> productVariants = new ArrayList<>();

    public int getTotalStock() {
        return productVariants.stream()
                .mapToInt(v -> v.getQuantity() != null ? v.getQuantity() : 0)
                .sum();
    }

    public void addVariant(ProductVariant variant) {
        if (productVariants == null) {
            productVariants = new ArrayList<>();
        }
        productVariants.add(variant);
    }



}

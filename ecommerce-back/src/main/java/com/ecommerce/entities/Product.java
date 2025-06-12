package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product extends Base{
    private String name;
    private Double buyPrice;
    private Double sellPrice;
    private String description;
    private Boolean state;

    @Enumerated(EnumType.STRING)
    private ProductGenreENUM genre;


    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "id_product"), inverseJoinColumns = @JoinColumn(name = "id_category"))
    private Set<Category> categories = new HashSet<>();
}

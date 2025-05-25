package com.ecommerce.entities;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category extends Base{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_gallery")
    private Gallery gallery;

    @ManyToMany
    @JoinTable(name = "category_type", joinColumns = @JoinColumn(name = "id_category"), inverseJoinColumns = @JoinColumn(name = "id_type"))
    private Set<Type> types = new HashSet<>();

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();
}

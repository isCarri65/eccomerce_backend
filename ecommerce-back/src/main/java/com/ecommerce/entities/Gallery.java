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
@Table(name = "gallery")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Gallery extends Base{

    private String image;

    @OneToMany(mappedBy = "gallery")
    private Set<ProductGallery> productGalleries = new HashSet<>();
}

package com.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "galleryProduct")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductGallery extends Base{
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
}

package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_gallery")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductGallery extends Base{
    private String state;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_gallery")
    private Gallery gallery;
}

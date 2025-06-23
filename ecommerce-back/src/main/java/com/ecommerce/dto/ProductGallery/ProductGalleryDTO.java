package com.ecommerce.dto.ProductGallery;

import com.ecommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductGalleryDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private Boolean isMain;
    private Long productId;
}

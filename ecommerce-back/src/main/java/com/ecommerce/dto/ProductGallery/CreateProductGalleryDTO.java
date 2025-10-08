package com.ecommerce.dto.ProductGallery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductGalleryDTO {
    private String name;
    private String imageUrl;
    private Boolean isMain;
    private Long productId;
    private String publicId;
}

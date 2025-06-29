package com.ecommerce.dto.ProductGallery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductGalleryAdminDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private Boolean isMain;
    private Long productId;
    private String publicId;
    private boolean deleted;
}

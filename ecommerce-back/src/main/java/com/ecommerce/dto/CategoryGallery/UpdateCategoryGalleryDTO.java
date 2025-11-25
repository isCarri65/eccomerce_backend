package com.ecommerce.dto.CategoryGallery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCategoryGalleryDTO {
    private String imageUrl;
    private String publicId;
    private String name;
    private Boolean isMain;
    private Long categoryId;
}

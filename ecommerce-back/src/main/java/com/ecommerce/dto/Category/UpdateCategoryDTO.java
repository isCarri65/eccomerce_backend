package com.ecommerce.dto.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCategoryDTO {
    private String name;
    private String imageUrl;
    private String publicId;
    private boolean deleted;

    private Long typeId;
}

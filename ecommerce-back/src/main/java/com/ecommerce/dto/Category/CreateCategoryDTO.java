package com.ecommerce.dto.Category;

import com.ecommerce.entities.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryDTO {
    private String name;
    private String imageUrl;
    private String publicId;
    private List<String> tags;
    private Long typeId;
}

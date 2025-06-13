package com.ecommerce.dto.Category;
// CategoryDTO.java (minimal to avoid cyclic reference)
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
}

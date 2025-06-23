package com.ecommerce.mappers;

import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.entities.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper implements BaseMapper<Category, CategoryDTO> {

    @Override
    public CategoryDTO toDTO(Category category) {
        TypeMapper typeMapper = new TypeMapper();
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setImageUrl(category.getImageUrl());
        dto.setTypes(category.getTypes().stream().map(typeMapper::toDTO).collect(Collectors.toList()));

        return dto;

    }
}

package com.ecommerce.mappers;

import com.ecommerce.dto.Category.CategoryAdminDTO;
import com.ecommerce.dto.Category.CreateCategoryDTO;
import com.ecommerce.dto.Category.UpdateCategoryDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Type;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryAdminMapper implements BaseAdminMapper<Category, Long, CategoryAdminDTO, CreateCategoryDTO, UpdateCategoryDTO> {
    @Override
    public CategoryAdminDTO toDTO(Category category) {

        CategoryAdminDTO dto = new CategoryAdminDTO();
        dto.setDeleted(category.isDeleted());
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setImageUrl(category.getImageUrl());
        dto.setPublicId(category.getPublicId());
        dto.setTypes(category.getTypes().stream().toList());

        return dto;

    }
    @Override
    public Category UDTOtoEntity (UpdateCategoryDTO updateDTO, Long id){
        Category category = new Category();
        category.setId(id);
        category.setName(updateDTO.getName());
        category.setImageUrl(updateDTO.getImageUrl());
        category.setPublicId(updateDTO.getPublicId());
        category.setDeleted(updateDTO.isDeleted());
        return category;
    }
    @Override
    public Category CDTOtoEntity(CreateCategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setImageUrl(categoryDTO.getImageUrl());
        category.setPublicId(categoryDTO.getPublicId());
        return category;
    }
}

package com.ecommerce.mappers;

import com.ecommerce.dto.Category.CategoryAdminDTO;
import com.ecommerce.dto.Category.CreateCategoryDTO;
import com.ecommerce.dto.Category.UpdateCategoryDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryAdminMapper implements BaseAdminMapper<Category, CategoryAdminDTO, CreateCategoryDTO, UpdateCategoryDTO> {
    private final TypeMapper typeMapper;
    @Override
    public CategoryAdminDTO toDTO(Category category) {

        CategoryAdminDTO dto = new CategoryAdminDTO();
        dto.setDeleted(category.isDeleted());
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setImageUrl(category.getImageUrl());
        dto.setPublicId(category.getPublicId());
        dto.setTags(category.getTags());
        if (category.getType() != null) {
            dto.setType(typeMapper.toDTO(category.getType()));
        }
        return dto;

    }
    @Override
    public void UDTOtoEntity (UpdateCategoryDTO updateDTO, Category category) {
        category.setName(updateDTO.getName());
        category.setImageUrl(updateDTO.getImageUrl());
        category.setPublicId(updateDTO.getPublicId());
        category.setDeleted(updateDTO.isDeleted());
        category.setTags(updateDTO.getTags());
        Type type = new Type();
        type.setId(updateDTO.getTypeId());
        category.setType(type);
    }
    @Override
    public Category CDTOtoEntity(CreateCategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setImageUrl(categoryDTO.getImageUrl());
        category.setPublicId(categoryDTO.getPublicId());
        category.setTags(categoryDTO.getTags());
        Type type = new Type();
        type.setId(categoryDTO.getTypeId());
        category.setType(type);
        return category;
    }
}

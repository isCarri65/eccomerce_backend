package com.ecommerce.mappers;

import com.ecommerce.dto.CategoryGallery.CategoryGalleryAdminDTO;
import com.ecommerce.dto.CategoryGallery.CategoryGalleryDTO;
import com.ecommerce.dto.CategoryGallery.CreateCategoryGalleryDTO;
import com.ecommerce.dto.CategoryGallery.UpdateCategoryGalleryDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.CategoryGallery;
import org.springframework.stereotype.Component;

@Component
public class CategoryGalleryMapper implements BaseAdminMapper<CategoryGallery, CategoryGalleryAdminDTO, CreateCategoryGalleryDTO, UpdateCategoryGalleryDTO> {
    
    public CategoryGalleryDTO toPublicDTO(CategoryGallery categoryGallery) {
        return CategoryGalleryDTO.builder()
                .id(categoryGallery.getId())
                .imageUrl(categoryGallery.getImageUrl())
                .publicId(categoryGallery.getPublicId())
                .name(categoryGallery.getName())
                .isMain(categoryGallery.getIsMain())
                .categoryId(categoryGallery.getCategory() != null ? categoryGallery.getCategory().getId() : null)
                .build();
    }
    
    @Override
    public CategoryGalleryAdminDTO toDTO(CategoryGallery categoryGallery) {
        return CategoryGalleryAdminDTO.builder()
                .id(categoryGallery.getId())
                .imageUrl(categoryGallery.getImageUrl())
                .publicId(categoryGallery.getPublicId())
                .name(categoryGallery.getName())
                .isMain(categoryGallery.getIsMain())
                .categoryId(categoryGallery.getCategory() != null ? categoryGallery.getCategory().getId() : null)
                .state(!categoryGallery.isDeleted())
                .build();
    }
    
    @Override
    public CategoryGallery CDTOtoEntity(CreateCategoryGalleryDTO dto) {
        Category category = new Category();
        category.setId(dto.getCategoryId());
        
        return CategoryGallery.builder()
                .imageUrl(dto.getImageUrl())
                .publicId(dto.getPublicId())
                .name(dto.getName())
                .isMain(dto.getIsMain())
                .category(category)
                .build();
    }
    
    @Override
    public void UDTOtoEntity(UpdateCategoryGalleryDTO dto, CategoryGallery categoryGallery) {
        categoryGallery.setImageUrl(dto.getImageUrl());
        categoryGallery.setPublicId(dto.getPublicId());
        categoryGallery.setName(dto.getName());
        categoryGallery.setIsMain(dto.getIsMain());
        
        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(dto.getCategoryId());
            categoryGallery.setCategory(category);
        }
    }
    
    public CategoryGallery toEntity(CategoryGalleryDTO dto) {
        return CategoryGallery.builder()
                .imageUrl(dto.getImageUrl())
                .publicId(dto.getPublicId())
                .name(dto.getName())
                .isMain(dto.getIsMain())
                .build();
    }
}

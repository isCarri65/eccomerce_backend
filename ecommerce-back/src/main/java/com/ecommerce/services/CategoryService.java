package com.ecommerce.services;

import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.mappers.CategoryAdminMapper;
import com.ecommerce.mappers.CategoryMapper;
import com.ecommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService extends BaseService<Category, Long>{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        super(categoryRepository);
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllByTypeId(Long typeId){
        return categoryRepository.findByTypeIdAndDeleted(typeId, false).stream().map(categoryMapper::toDTO).collect(Collectors.toList());
    }

    public List<CategoryDTO> getCategoriesByTag(String tag) {

        List<Category> categories = categoryRepository.findByTag(tag);
        return categories.stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Set<Category> findAllByListId(Set<Long> ids){
        return categoryRepository.getAllByIdIn(ids);
    }
}

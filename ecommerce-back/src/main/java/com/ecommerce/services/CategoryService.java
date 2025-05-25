package com.ecommerce.services;

import com.ecommerce.entities.Category;
import com.ecommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, Long>{
    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }
}

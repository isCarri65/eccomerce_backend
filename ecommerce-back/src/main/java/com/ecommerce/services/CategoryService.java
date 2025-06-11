package com.ecommerce.services;

import com.ecommerce.entities.Category;
import com.ecommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService extends BaseService<Category, Long> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    public Category create(Category category) {
        category.setDeleted(false);
        return categoryRepository.save(category);
    }

    public Optional<Category> getById(Long id) {
        return categoryRepository.findByIdAndDeletedFalse(id);
    }

    public List<Category> getAll() {
        return categoryRepository.findAllByDeletedFalse();
    }

    public Category update(Long id, Category updated) {
        Category existing = categoryRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        existing.setName(updated.getName());
        return categoryRepository.save(existing);
    }

    public void delete(Long id) {
        Category existing = categoryRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        existing.setDeleted(true);
        categoryRepository.save(existing);
    }
}

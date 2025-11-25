package com.ecommerce.services;

import com.ecommerce.entities.CategoryGallery;
import com.ecommerce.repositories.CategoryGalleryRepository;
import com.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryGalleryService extends BaseService<CategoryGallery, Long> {
    
    private final CategoryGalleryRepository categoryGalleryRepository;
    private final CategoryRepository categoryRepository;
    
    public CategoryGalleryService(CategoryGalleryRepository categoryGalleryRepository, CategoryRepository categoryRepository) {
        super(categoryGalleryRepository);
        this.categoryGalleryRepository = categoryGalleryRepository;
        this.categoryRepository = categoryRepository;
    }
    
    public List<CategoryGallery> getAllByCategoryId(Long categoryId) {
        return categoryGalleryRepository.getAllByCategoryId(categoryId);
    }
    
    public Optional<CategoryGallery> findMainImageByCategoryId(Long categoryId) {
        return categoryGalleryRepository.findMainImageByCategoryId(categoryId);
    }
    
    public CategoryGallery createWithCategory(CategoryGallery categoryGallery, Long categoryId) {
        categoryRepository.findById(categoryId).ifPresent(categoryGallery::setCategory);
        return create(categoryGallery);
    }
    
    public void setAsMainImage(Long galleryId, Long categoryId) {
        // Primero desmarcar todas las imágenes principales de la categoría
        List<CategoryGallery> allImages = getAllByCategoryId(categoryId);
        allImages.forEach(img -> img.setIsMain(false));
        categoryGalleryRepository.saveAll(allImages);
        
        // Marcar la imagen seleccionada como principal
        Optional<CategoryGallery> mainImage = findByIdActive(galleryId);
        if (mainImage.isPresent()) {
            mainImage.get().setIsMain(true);
            update(galleryId, mainImage.get());
        }
    }
}

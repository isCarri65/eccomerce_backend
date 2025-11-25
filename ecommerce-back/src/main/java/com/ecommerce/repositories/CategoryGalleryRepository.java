package com.ecommerce.repositories;

import com.ecommerce.entities.CategoryGallery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryGalleryRepository extends BaseRepository<CategoryGallery, Long> {
    
    List<CategoryGallery> findByCategoryIdAndDeletedFalse(Long categoryId);
    
    @Query("SELECT cg FROM CategoryGallery cg WHERE cg.category.id = :categoryId AND cg.deleted = false")
    List<CategoryGallery> getAllByCategoryId(@Param("categoryId") Long categoryId);
    
    @Query("SELECT cg FROM CategoryGallery cg WHERE cg.category.id = :categoryId AND cg.isMain = true AND cg.deleted = false")
    Optional<CategoryGallery> findMainImageByCategoryId(@Param("categoryId") Long categoryId);
}

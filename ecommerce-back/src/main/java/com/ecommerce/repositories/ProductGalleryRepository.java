package com.ecommerce.repositories;

import com.ecommerce.entities.ProductGallery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductGalleryRepository extends BaseRepository<ProductGallery, Long> {
    Optional<ProductGallery> findByProduct_IdAndIsMain(Long productId, boolean isMain);
    List<ProductGallery> findByProductId(Long productId);
}

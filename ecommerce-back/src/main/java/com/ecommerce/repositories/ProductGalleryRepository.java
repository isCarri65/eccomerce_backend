package com.ecommerce.repositories;

import com.ecommerce.entities.ProductGallery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGalleryRepository extends BaseRepository<ProductGallery, Long> {
    List<ProductGallery> findByProductId(Long productId);
}

package com.ecommerce.repositories;

import com.ecommerce.entities.GalleryProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryProductRepository extends BaseRepository<GalleryProduct, Long> {
    List<GalleryProduct> findByProductId(Long productId);
}

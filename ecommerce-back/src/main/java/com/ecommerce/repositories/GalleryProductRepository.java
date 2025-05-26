package com.ecommerce.repositories;

import com.ecommerce.entities.GalleryProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryProductRepository extends BaseRepository<GalleryProduct, Long> {
}

package com.ecommerce.repositories;

import com.ecommerce.entities.ProductGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGalleryRepository extends JpaRepository<ProductGallery, Long> {

}

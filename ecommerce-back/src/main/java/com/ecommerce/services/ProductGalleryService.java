package com.ecommerce.services;

import com.ecommerce.entities.ProductGallery;
import com.ecommerce.repositories.ProductGalleryRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductGalleryService extends BaseService<ProductGallery, Long> {
    public ProductGalleryService(ProductGalleryRepository galleryProductRepository) {
        super(galleryProductRepository);
    }
}
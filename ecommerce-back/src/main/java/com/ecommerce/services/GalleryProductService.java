package com.ecommerce.services;

import com.ecommerce.entities.GalleryProduct;
import com.ecommerce.repositories.GalleryProductRepository;
import org.springframework.stereotype.Service;

@Service
public class GalleryProductService extends BaseService<GalleryProduct, Long> {
    public GalleryProductService(GalleryProductRepository galleryProductRepository) {
        super(galleryProductRepository);
    }
}
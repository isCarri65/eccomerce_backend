package com.ecommerce.services;

import com.ecommerce.entities.ProductGallery;
import com.ecommerce.repositories.ProductGalleryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGalleryService extends BaseService<ProductGallery, Long> {
    ProductGalleryRepository productGalleryRepository;
    public ProductGalleryService(ProductGalleryRepository productGalleryRepository) {
        super(productGalleryRepository);
        this.productGalleryRepository = productGalleryRepository;
    }

    public List<ProductGallery> getImageByProductId(Long productId) {
        return productGalleryRepository.findByProductId(productId);
    }
}
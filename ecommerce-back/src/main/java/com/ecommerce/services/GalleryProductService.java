package com.ecommerce.services;

import com.ecommerce.entities.GalleryProduct;
import com.ecommerce.repositories.GalleryProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryProductService extends BaseService<GalleryProduct, Long> {

    private final GalleryProductRepository galleryProductRepository;

    public GalleryProductService(GalleryProductRepository galleryProductRepository) {
        super(galleryProductRepository);
        this.galleryProductRepository = galleryProductRepository;
    }

    public List<GalleryProduct> obtenerImagenesPorProducto(Long productId) {
        return galleryProductRepository.findByProductId(productId);
    }
}
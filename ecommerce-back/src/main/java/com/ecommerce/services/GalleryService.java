package com.ecommerce.services;

import com.ecommerce.entities.Gallery;
import com.ecommerce.repositories.GalleryRepository;
import org.springframework.stereotype.Service;

@Service
public class GalleryService extends BaseService<Gallery, Long>{
    public GalleryService(GalleryRepository galleryRepository) {
        super(galleryRepository);
    }
}

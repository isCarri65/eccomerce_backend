package com.ecommerce.controllers;

import com.ecommerce.entities.GalleryProduct;
import com.ecommerce.services.GalleryProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/galleryProducts")
public class GalleryProductController extends BaseController<GalleryProduct, Long> {
    public GalleryProductController(GalleryProductService galleryProductService) {
        super(galleryProductService);
    }
}

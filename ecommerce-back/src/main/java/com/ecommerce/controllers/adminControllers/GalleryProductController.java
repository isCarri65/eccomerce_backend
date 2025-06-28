package com.ecommerce.controllers.adminControllers;

import com.ecommerce.entities.ProductGallery;
import com.ecommerce.services.ProductGalleryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/galleryProducts")
public class GalleryProductController extends BaseController<ProductGallery, Long> {
    public GalleryProductController(ProductGalleryService productGalleryService) {
        super(productGalleryService);
    }
}

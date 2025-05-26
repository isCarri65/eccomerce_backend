package com.ecommerce.controllers;

import com.ecommerce.entities.Gallery;
import com.ecommerce.services.GalleryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/galleries")
public class GalleryController extends BaseController<Gallery, Long> {
    public GalleryController(GalleryService galleryService) {
        super(galleryService);
    }
}

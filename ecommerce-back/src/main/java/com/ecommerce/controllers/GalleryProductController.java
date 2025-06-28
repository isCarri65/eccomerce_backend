package com.ecommerce.controllers;

import com.ecommerce.entities.GalleryProduct;
import com.ecommerce.services.GalleryProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/galleryProducts")
public class GalleryProductController extends BaseController<GalleryProduct, Long> {
    private final GalleryProductService galleryProductService;

    public GalleryProductController(GalleryProductService galleryProductService) {
        super(galleryProductService);
        this.galleryProductService = galleryProductService;
    }

    @GetMapping("/product/{productId}")
    public List<GalleryProduct> obtenerImagenesPorProducto(@PathVariable Long productId) {
        return galleryProductService.obtenerImagenesPorProducto(productId);
    }
}

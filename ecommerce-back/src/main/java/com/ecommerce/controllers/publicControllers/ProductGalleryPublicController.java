
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.entities.ProductGallery;
import com.ecommerce.services.ProductGalleryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/public/productgalleries")
public class ProductGalleryPublicController {
    private final ProductGalleryService service;

    public ProductGalleryPublicController(ProductGalleryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<ProductGallery>> getAllActives() {
        return ResponseEntity.ok(service.getAllActives());
    }

    @GetMapping("/{id}") // Doble llave para escapar en format()
    public ResponseEntity<ProductGallery> getByIdActives(@PathVariable Long id) {
        return service.findByIdActive(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductGallery>> getByProductId(@PathVariable("productId") Long productId) {
        List<ProductGallery> images = service.getAllByProductId(productId);
        if (images.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(images);
    }



}

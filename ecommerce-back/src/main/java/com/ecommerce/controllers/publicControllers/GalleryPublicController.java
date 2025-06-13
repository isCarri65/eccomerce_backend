
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.entities.Gallery;
import com.ecommerce.services.GalleryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/public/galleries")
public class GalleryPublicController {
    private final GalleryService service;

    public GalleryPublicController(GalleryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<Gallery>> getAllActives() {
        return ResponseEntity.ok(service.getAllActives());
    }

    @GetMapping("/{id}") // Doble llave para escapar en format()
    public ResponseEntity<Gallery> getByIdActives(@PathVariable Long id) {
        return service.findByIdActive(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

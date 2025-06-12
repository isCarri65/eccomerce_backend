
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.entities.Product;
import com.ecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/public/products")
public class ProductPublicController {
    private final ProductService service;

    public ProductPublicController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<Product>> getAllActives() {
        return ResponseEntity.ok(service.getAllActives());
    }

    @GetMapping("/{id}") // Doble llave para escapar en format()
    public ResponseEntity<Product> getByIdActives(@PathVariable Long id) {
        return service.findByIdActive(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

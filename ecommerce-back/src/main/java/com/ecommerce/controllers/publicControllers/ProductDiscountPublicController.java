
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.entities.ProductDiscount;
import com.ecommerce.services.ProductDiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/public/productdiscounts")
public class ProductDiscountPublicController {
    private final ProductDiscountService service;

    public ProductDiscountPublicController(ProductDiscountService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<ProductDiscount>> getAllActives() {
        return ResponseEntity.ok(service.getAllActives());
    }

    @GetMapping("/{id}") // Doble llave para escapar en format()
    public ResponseEntity<ProductDiscount> getByIdActives(@PathVariable Long id) {
        return service.findByIdActive(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

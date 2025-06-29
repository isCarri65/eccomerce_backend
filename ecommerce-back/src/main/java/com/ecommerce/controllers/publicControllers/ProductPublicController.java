
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.dto.Product.ProductDTO;
import com.ecommerce.dto.Product.ProductListDTO;
import com.ecommerce.dto.ProductFilterDTO;
import com.ecommerce.entities.Product;
import com.ecommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public/products")
public class ProductPublicController {
    private final ProductService service;

    public ProductPublicController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}") // Doble llave para escapar en format()
    public ResponseEntity<ProductDTO> getByIdActives(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductPublicById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductListDTO>> getAllProducts() {
        return ResponseEntity.ok(service.getAll().stream()
            .map(service::getProductListDTO)
            .collect(Collectors.toList()));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProductListDTO>> getFilteredProducts(
            @ModelAttribute ProductFilterDTO filter,
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.getFilteredProducts(filter, pageable)
                .map(service::getProductListDTO));
    }

}


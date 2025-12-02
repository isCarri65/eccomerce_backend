
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.dto.Product.ProductDTO;
import com.ecommerce.dto.Product.ProductListDTO;
import com.ecommerce.dto.ProductFilterDTO;
import com.ecommerce.dto.productVariant.ProductVariantDTO;
import com.ecommerce.entities.Product;
import com.ecommerce.services.ProductService;
import com.ecommerce.dto.Product.CreateProductDTO;
import com.ecommerce.dto.Product.ProductAdminDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public/products")
public class ProductPublicController {
    private final ProductService service;
    private final ProductService productService;

    public ProductPublicController(ProductService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @GetMapping("/{id}") // Doble llave para escapar en format()
    public ResponseEntity<ProductDTO> getByIdActives(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductPublicById(id));
    }
    @GetMapping("/{productId}/variants")
    public ResponseEntity<List<ProductVariantDTO>> getProductVariants(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getVariantsByProductId(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(service.getAll().stream()
            .map(service::getProductDTO)
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

    @GetMapping("/search")
    public ResponseEntity<Page<ProductListDTO>> getSearchedProducts(@RequestParam String search, Pageable pageable) {
        return ResponseEntity.ok(productService.searchProductsByName(search, pageable).map(service::getProductListDTO));
    }

    @PostMapping(    
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductAdminDTO> create(@ModelAttribute CreateProductDTO product) {
        return ResponseEntity.ok(productService.createProductWhitGalleries(product));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.update(id, product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "categoryIds", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                List<Long> ids = Arrays.stream(text.split(","))
                        .filter(s -> !s.isBlank())
                        .map(Long::parseLong)
                        .collect(Collectors.toList());
                setValue(ids);
            }
        });
    }


}


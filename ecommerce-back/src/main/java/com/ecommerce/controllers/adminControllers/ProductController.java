package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.Product.CreateProductDTO;
import com.ecommerce.dto.Product.ProductAdminDTO;
import com.ecommerce.dto.Product.ProductDTO;
import com.ecommerce.dto.Product.UpdateProductDTO;
import com.ecommerce.entities.Product;
import com.ecommerce.mappers.ProductAdminMapper;
import com.ecommerce.mappers.ProductMapper;
import com.ecommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/products")
public class ProductController extends BaseController<Product, Long, ProductAdminDTO, CreateProductDTO, UpdateProductDTO> {
    private final ProductService productService;
    private final ProductAdminMapper mapper;

    public ProductController(ProductService productService, ProductAdminMapper productMapper) {
        super(productService, productMapper);
        this.productService = productService;
        this.mapper = productMapper;
    }

    @PostMapping("/createWithImages")
    public ResponseEntity<ProductAdminDTO> createProductWithImages(@ModelAttribute CreateProductDTO dto){
        return ResponseEntity.ok(productService.createProductWithImages(dto));
    }
    
    @PutMapping("/{id}/updateWithImages")
    public ResponseEntity<ProductAdminDTO> updateProductWithImages(@PathVariable Long id, @ModelAttribute UpdateProductDTO dto){
        return ResponseEntity.ok(productService.updateProductWithImages(id, dto));
    }

    @PostMapping("/createWhitImages")
    public ResponseEntity<ProductAdminDTO> createProductWhitGalleries(@RequestBody CreateProductDTO dto){
        return ResponseEntity.ok(productService.createProductWhitGalleries(dto));
    }

    @Override
    @GetMapping("/{id}") // Doble llave para escapar en format()
    public ResponseEntity<ProductAdminDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findProductAdminById(id, mapper::toDTO ));
    }

}

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

    public ProductController(ProductService productService, ProductAdminMapper productMapper) {
        super(productService, productMapper);
        this.productService = productService;
    }

    @PostMapping("/createWhitImages")
    public ResponseEntity<ProductAdminDTO> createProductWhitGalleries(@RequestBody CreateProductDTO dto){
        return ResponseEntity.ok(productService.createProductWhitGalleries(dto));
    }


}

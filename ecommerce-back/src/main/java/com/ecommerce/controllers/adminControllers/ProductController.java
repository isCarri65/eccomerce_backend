package com.ecommerce.controllers;

import com.ecommerce.entities.Product;
import com.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController extends BaseController<Product, Long> {
    public ProductController(ProductService productService) {
        super(productService);
    }
}

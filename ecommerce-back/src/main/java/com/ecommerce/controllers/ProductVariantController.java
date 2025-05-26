package com.ecommerce.controllers;

import com.ecommerce.entities.Base;
import com.ecommerce.entities.ProductVariant;
import com.ecommerce.services.ProductVariantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtVariants")
public class ProductVariantController extends BaseController<ProductVariant, Long> {
    public ProductVariantController(ProductVariantService productVariantService) {
        super(productVariantService);
    }
}

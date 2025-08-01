
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.dto.productVariant.ProductVariantCartDTO;
import com.ecommerce.dto.productVariant.ProductVariantDTO;
import com.ecommerce.entities.ProductVariant;
import com.ecommerce.mappers.ProductVariantMapper;
import com.ecommerce.services.ProductVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/public/productVariants")
public class ProductVariantPublicController  extends  BasePublicController<ProductVariant,Long, ProductVariantDTO> {

    private final ProductVariantService productVariantService;
    public ProductVariantPublicController(ProductVariantService service , ProductVariantMapper mapper) {
        super(service, mapper);
        this.productVariantService = service;
    }
    @GetMapping("/cart-dto/{id}")
    public ResponseEntity<ProductVariantCartDTO> getCartDTOById(@PathVariable Long id){
        return ResponseEntity.ok(productVariantService.getCartDTOById(id));
    }
}

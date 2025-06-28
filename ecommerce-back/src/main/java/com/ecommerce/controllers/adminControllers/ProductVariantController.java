package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.productVariant.CreateProductVariantDTO;
import com.ecommerce.dto.productVariant.ProductVariantAdminDTO;
import com.ecommerce.dto.productVariant.UpdateProductVariantDTO;
import com.ecommerce.entities.ProductVariant;
import com.ecommerce.mappers.ProductVariantAdminMapper;
import com.ecommerce.services.ProductVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/productVariants")
public class ProductVariantController extends BaseController<ProductVariant, Long, ProductVariantAdminDTO, CreateProductVariantDTO, UpdateProductVariantDTO> {
    private final ProductVariantService productVariantService;

    public ProductVariantController(ProductVariantService productVariantService, ProductVariantAdminMapper mapper) {
        super(productVariantService, mapper);
        this.productVariantService = productVariantService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductVariantAdminDTO> createVariant(@RequestBody CreateProductVariantDTO createProductVariantDTO) {
        return ResponseEntity.ok(productVariantService.createVariant(createProductVariantDTO.getProductId(), createProductVariantDTO));
    }
}

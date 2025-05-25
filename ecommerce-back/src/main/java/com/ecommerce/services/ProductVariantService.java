package com.ecommerce.services;

import com.ecommerce.entities.ProductVariant;
import com.ecommerce.repositories.ProductVariantRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductVariantService extends BaseService<ProductVariant, Long> {
    public ProductVariantService(ProductVariantRepository productVariantRepository) {
        super(productVariantRepository);
    }
}

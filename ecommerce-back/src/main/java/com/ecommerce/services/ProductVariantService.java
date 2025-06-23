package com.ecommerce.services;

import com.ecommerce.entities.ProductVariant;
import com.ecommerce.repositories.ProductVariantRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductVariantService extends BaseService<ProductVariant, Long> {
    private final ProductVariantRepository productVariantRepository;

    public ProductVariantService( ProductVariantRepository productVariantRepository) {
        super(productVariantRepository);
        this.productVariantRepository = productVariantRepository;
    }
    public Boolean hasStockAvalibleByProducId(Long id) {
        return productVariantRepository.existsByProductIdAndQuantityGreaterThanAndStateTrue(id, 0);
    }
}

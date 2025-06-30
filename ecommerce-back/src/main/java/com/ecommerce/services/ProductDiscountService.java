package com.ecommerce.services;

import com.ecommerce.entities.ProductDiscount;
import com.ecommerce.repositories.ProductDiscountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDiscountService extends BaseService<ProductDiscount, Long> {
    private final ProductDiscountRepository productDiscountRepository;
    public ProductDiscountService(ProductDiscountRepository productDiscountRepository) {
        super(productDiscountRepository);
        this.productDiscountRepository = productDiscountRepository;
    }
    public List<ProductDiscount> getDiscountByProductId(Long productId) {
        return productDiscountRepository.findByProductId(productId);
    }
}

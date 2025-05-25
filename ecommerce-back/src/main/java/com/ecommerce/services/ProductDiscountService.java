package com.ecommerce.services;

import com.ecommerce.entities.ProductDiscount;
import com.ecommerce.repositories.ProductDiscountRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductDiscountService extends BaseService<ProductDiscount, Long> {
    public ProductDiscountService(ProductDiscountRepository productDiscountRepository) {
        super(productDiscountRepository);
    }
}

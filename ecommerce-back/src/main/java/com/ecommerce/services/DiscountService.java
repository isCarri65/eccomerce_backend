package com.ecommerce.services;

import com.ecommerce.entities.Discount;
import com.ecommerce.repositories.DiscountRepository;
import org.springframework.stereotype.Service;

@Service
public class DiscountService extends BaseService<Discount, Long> {
    public DiscountService(DiscountRepository discountRepository) {
        super(discountRepository);
    }
}

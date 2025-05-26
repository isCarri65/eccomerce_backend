package com.ecommerce.controllers;

import com.ecommerce.entities.ProductDiscount;
import com.ecommerce.services.ProductDiscountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productDiscounts")
public class ProductDiscountController extends BaseController<ProductDiscount, Long> {
    public ProductDiscountController(ProductDiscountService productDiscountService) {
        super(productDiscountService);
    }

}

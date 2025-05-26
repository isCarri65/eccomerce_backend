package com.ecommerce.controllers;

import com.ecommerce.entities.Discount;
import com.ecommerce.services.DiscountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController extends BaseController<Discount, Long> {
    public DiscountController(DiscountService discountService){
        super(discountService);
    }

}

package com.ecommerce.controllers.adminControllers;

import com.ecommerce.entities.DiscountRule;
import com.ecommerce.services.DiscountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/discounts")
public class DiscountController extends BaseSimpleController<DiscountRule, Long> {
    public DiscountController(DiscountService discountService){
        super(discountService);
    }

}

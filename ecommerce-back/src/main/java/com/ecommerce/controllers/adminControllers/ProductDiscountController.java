package com.ecommerce.controllers.adminControllers;

import com.ecommerce.entities.ProductDiscount;
import com.ecommerce.services.ProductDiscountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/productDiscounts")
public class ProductDiscountController extends BaseController<ProductDiscount, Long> {
    public ProductDiscountController(ProductDiscountService productDiscountService) {
        super(productDiscountService);
    }

}

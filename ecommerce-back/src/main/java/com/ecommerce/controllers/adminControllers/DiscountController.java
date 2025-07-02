package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.Discount.CreateDiscountRuleDTO;
import com.ecommerce.dto.Discount.DiscountRuleAdminDTO;
import com.ecommerce.dto.Discount.UpdateDiscountRuleDTO;
import com.ecommerce.entities.DiscountRule;
import com.ecommerce.mappers.DiscountRuleAdminMapper;
import com.ecommerce.services.DiscountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/discounts")
public class DiscountController extends BaseController<DiscountRule, Long, DiscountRuleAdminDTO, CreateDiscountRuleDTO, UpdateDiscountRuleDTO> {

    public DiscountController(DiscountService discountService, DiscountRuleAdminMapper discountRuleAdminMapper) {
        super(discountService, discountRuleAdminMapper);
    }

}

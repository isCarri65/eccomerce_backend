
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.dto.Discount.DiscountRuleDTO;
import com.ecommerce.entities.DiscountRule;
import com.ecommerce.mappers.DiscountRuleMapper;
import com.ecommerce.services.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/public/discounts")
public class DiscountPublicController extends BasePublicController<DiscountRule, Long, DiscountRuleDTO> {

    public DiscountPublicController(DiscountService service, DiscountRuleMapper mapper) {
        super(service,mapper );
    }


}

package com.ecommerce.mappers;

import com.ecommerce.dto.Discount.DiscountRuleDTO;
import com.ecommerce.entities.DiscountRule;
import org.springframework.stereotype.Component;

@Component
public class DiscountRuleMapper implements BaseMapper<DiscountRule, DiscountRuleDTO> {
    public DiscountRuleDTO toDTO(DiscountRule discountRule) {
        DiscountRuleDTO dto = new DiscountRuleDTO();
        dto.setId(discountRule.getId());
        dto.setState(discountRule.getState());
        dto.setPercentage(discountRule.getPercentage());
        dto.setStartDate(discountRule.getStartDate());
        dto.setEndDate(discountRule.getEndDate());
        if(discountRule.getProduct() != null) dto.setProductId(discountRule.getProduct().getId());
        if(discountRule.getCategory() != null) dto.setCategoryId(discountRule.getCategory().getId());
        return dto;
    }
}

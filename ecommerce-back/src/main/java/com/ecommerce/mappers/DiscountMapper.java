package com.ecommerce.mappers;

import com.ecommerce.dto.Discount.DiscountDTO;
import com.ecommerce.entities.DiscountRule;

public class DiscountMapper implements BaseMapper<DiscountRule, DiscountDTO> {
    @Override
    public DiscountDTO toDTO(DiscountRule discount) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setId(discount.getId());
        discountDTO.setPercentage(discount.getPercentage());
        discountDTO.setState(discount.getState());
        discountDTO.setStartDate(discount.getStartDate());
        discountDTO.setEndDate(discount.getEndDate());
        return discountDTO;

    }
}

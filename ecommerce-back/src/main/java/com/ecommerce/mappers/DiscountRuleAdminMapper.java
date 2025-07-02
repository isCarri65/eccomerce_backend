package com.ecommerce.mappers;

import com.ecommerce.dto.Discount.CreateDiscountRuleDTO;
import com.ecommerce.dto.Discount.DiscountRuleAdminDTO;
import com.ecommerce.dto.Discount.DiscountRuleDTO;
import com.ecommerce.dto.Discount.UpdateDiscountRuleDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.DiscountRule;
import com.ecommerce.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class DiscountRuleAdminMapper implements BaseAdminMapper<DiscountRule, DiscountRuleAdminDTO, CreateDiscountRuleDTO, UpdateDiscountRuleDTO> {
    public DiscountRuleAdminDTO toDTO(DiscountRule discountRule) {
        DiscountRuleAdminDTO dto = new DiscountRuleAdminDTO();
        dto.setId(discountRule.getId());
        dto.setDeleted(discountRule.isDeleted());
        dto.setState(discountRule.getState());
        dto.setPercentage(discountRule.getPercentage());
        dto.setStartDate(discountRule.getStartDate());
        dto.setEndDate(discountRule.getEndDate());

        dto.setProductId(discountRule.getProduct().getId());
        dto.setCategoryId(discountRule.getCategory().getId());
        return dto;
    }
    public DiscountRule CDTOtoEntity(CreateDiscountRuleDTO dto) {
        DiscountRule discountRule = new DiscountRule();

        discountRule.setState(dto.getState());
        discountRule.setPercentage(dto.getPercentage());
        discountRule.setStartDate(dto.getStartDate());
        discountRule.setEndDate(dto.getEndDate());

        Product product = new Product();
        product.setId(dto.getProductId());
        discountRule.setProduct(product);

        Category category = new Category();
        category.setId(dto.getCategoryId());
        discountRule.setCategory(category);
        return discountRule;
    }

    public void UDTOtoEntity(UpdateDiscountRuleDTO dto, DiscountRule discountRule) {
        discountRule.setState(dto.getState());
        discountRule.setPercentage(dto.getPercentage());
        discountRule.setStartDate(dto.getStartDate());
        discountRule.setEndDate(dto.getEndDate());
        discountRule.setDeleted(dto.getDeleted());

        Product product = new Product();
        product.setId(dto.getProductId());
        discountRule.setProduct(product);
        Category category = new Category();
        category.setId(dto.getCategoryId());
        discountRule.setCategory(category);


    }
}

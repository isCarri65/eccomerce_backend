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

        dto.setProductId(discountRule.getProduct() != null ? discountRule.getProduct().getId() : null);
        dto.setCategoryId(discountRule.getCategory() != null ? discountRule.getCategory().getId(): null);
        return dto;
    }
    public DiscountRule CDTOtoEntity(CreateDiscountRuleDTO dto) {
        DiscountRule discountRule = new DiscountRule();

        discountRule.setState(dto.getState());
        discountRule.setPercentage(dto.getPercentage());
        discountRule.setStartDate(dto.getStartDate());
        discountRule.setEndDate(dto.getEndDate());
        if (dto.getProductId() != null) {

            Product product = new Product();
            product.setId(dto.getProductId());
            discountRule.setProduct(product);
        }

        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(dto.getCategoryId());
            discountRule.setCategory(category);
        }
        return discountRule;
    }

    public void UDTOtoEntity(UpdateDiscountRuleDTO dto, DiscountRule discountRule) {
        discountRule.setState(dto.getState());
        discountRule.setPercentage(dto.getPercentage());
        discountRule.setStartDate(dto.getStartDate());
        discountRule.setEndDate(dto.getEndDate());
        discountRule.setDeleted(dto.getDeleted());

        if (dto.getProductId() != null) {
            Product product = new Product();
            product.setId(dto.getProductId());

            discountRule.setProduct(product);
        } else {
            discountRule.setProduct(null);
        }

        if (dto.getCategoryId() != null) {

            Category category = new Category();
            category.setId(dto.getCategoryId());

            discountRule.setCategory(category);
        } else {
            discountRule.setCategory(null);
        }


    }
}

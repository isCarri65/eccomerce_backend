package com.ecommerce.dto.Discount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDiscountRuleDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal percentage;
    private Boolean state;
    private Long categoryId;
    private Long productId;
}

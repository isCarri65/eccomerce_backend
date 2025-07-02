package com.ecommerce.dto.Discount;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiscountRuleAdminDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double percentage;
    private Boolean state;
    private Long categoryId;
    private Boolean deleted;
    private Long productId;
}
package com.ecommerce.dto.Discount;

import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class DiscountRuleDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal percentage;
    private Boolean state;
    private Long categoryId;

    private Long productId;

}

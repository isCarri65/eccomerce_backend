package com.ecommerce.services;

import com.ecommerce.entities.DiscountRule;
import com.ecommerce.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductPricingCalculator {

    private final DiscountService discountService;

    @Transactional
    public void updateCalculatedAtributes (Product product) {
        BigDecimal basePrice = product.getSellPrice();

        Optional<DiscountRule> discountOpt = discountService.getBestApplicableDiscount(product);

        double score = 0;
        if (discountOpt.isPresent()) {
            DiscountRule discount = discountOpt.get();
            BigDecimal discountAmount = basePrice.multiply((discount.getPercentage()));
            product.setFinalPrice(basePrice.subtract(discountAmount));
            score += ((discount.getPercentage()) != null ? discount.getPercentage().multiply(BigDecimal.valueOf(50)).doubleValue() : 0);

        } else {
            product.setFinalPrice(basePrice);
        }
        score += product.getSalesCount() != null ? product.getSalesCount() * 0.5 : 0 ;
        LocalDateTime createdAt = product.getCreatedAt(); // tipo LocalDateTime
        long daysSince = ChronoUnit.DAYS.between(createdAt, LocalDateTime.now());
        if (daysSince < 30) score += 10;
        if (daysSince < 7) score += 20;

        product.setRecommendedScore(score);

    }
}

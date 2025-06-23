package com.ecommerce.services;

import com.ecommerce.entities.Category;
import com.ecommerce.entities.DiscountRule;
import com.ecommerce.entities.Product;
import com.ecommerce.repositories.DiscountRuleRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountService extends BaseService<DiscountRule, Long> {

    private final DiscountRuleRepository discountRuleRepository;

    public DiscountService(DiscountRuleRepository discountRuleRepository) {
        super(discountRuleRepository);
        this.discountRuleRepository = discountRuleRepository;

    }

    public Optional<DiscountRule> getBestApplicableDiscount(Product product) {
        List<Long> categoryIds = product.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toList());
        List<DiscountRule> applicableDiscounts = discountRuleRepository.findApplicableDiscountsForProduct(
                product.getId(),
                categoryIds.isEmpty() ? List.of(-1L) : categoryIds // evita error si no tiene categorías
        );

        return applicableDiscounts.stream()
                .max(Comparator.comparing(DiscountRule::getPercentage));
    }
}

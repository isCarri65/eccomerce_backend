package com.ecommerce.services;

import com.ecommerce.entities.Category;
import com.ecommerce.entities.DiscountRule;
import com.ecommerce.entities.Product;
import com.ecommerce.mappers.BaseAdminMapper;
import com.ecommerce.repositories.DiscountRuleRepository;
import com.ecommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DiscountService extends BaseService<DiscountRule, Long> {

    private final DiscountRuleRepository discountRuleRepository;
    private final ProductRepository productRepository;

    public DiscountService(DiscountRuleRepository discountRuleRepository, ProductRepository productRepository) {
        super(discountRuleRepository);
        this.discountRuleRepository = discountRuleRepository;
        this.productRepository = productRepository;
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

    @Override
    @Transactional
    public DiscountRule create(DiscountRule entity) {
        if (entity.getProduct() != null) {
            Product product = productRepository.findById(entity.getProduct().getId()).orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
            entity.setProduct(product);
        }
        discountRuleRepository.save(entity);
       updateProductsValues(entity);
        return entity;
    }

    @Override
    @Transactional
    public DiscountRule update(Long id, DiscountRule entity) {
        if (!baseRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        if (entity.getProduct() != null) {
            Product product = productRepository.findById(entity.getProduct().getId()).orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
            entity.setProduct(product);
        }
        entity.setId(id);
        baseRepository.save(entity);
        updateProductsValues(entity);
        return entity;
    }

    @Override
    @Transactional
    public <EntityDTO, CreateDTO, UpdateDTO> EntityDTO create(
            CreateDTO dto,
            BaseAdminMapper<DiscountRule, EntityDTO, CreateDTO, UpdateDTO> mapper) {

        DiscountRule entity = mapper.CDTOtoEntity(dto);
        if (entity.getProduct() != null) {
            Product product = productRepository.findById(entity.getProduct().getId()).orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
            entity.setProduct(product);
        }

        DiscountRule saved = baseRepository.save(entity);

        updateProductsValues(saved);
        return mapper.toDTO(saved);
    }

    @Transactional
    public <EntityDTO, CreateDTO, UpdateDTO> EntityDTO update(Long id, UpdateDTO dto, BaseAdminMapper<DiscountRule, EntityDTO, CreateDTO, UpdateDTO> mapper) {
        DiscountRule entity = baseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada para actualizar"));

        //actualizamos la entidad
        mapper.UDTOtoEntity(dto, entity);

        if (entity.getProduct() != null) {
            Product product = productRepository.findById(entity.getProduct().getId()).orElseThrow(()-> new EntityNotFoundException("Producto no encontrado"));
            entity.setProduct(product);
        }

        entity.setId(id);
        DiscountRule saved = baseRepository.save(entity);

        updateProductsValues(saved);

        return mapper.toDTO(saved);
    }
    private void updateProductsValues(DiscountRule entity) {
        if (entity.getProduct() != null) {
            updateFinalPriceAndScore(entity.getProduct());
        }
        if (entity.getCategory() != null) {
            Set<Product> products = productRepository.findProductsByCategory(entity.getCategory());
            products.forEach(this::updateFinalPriceAndScore);
        }
    }
    private void  updateFinalPriceAndScore(Product product) {
        BigDecimal basePrice = product.getSellPrice();

        Optional<DiscountRule> discountOpt = getBestApplicableDiscount(product);

        double score = 0;
        if (discountOpt.isPresent()) {
            DiscountRule discount = discountOpt.get();
            BigDecimal discountAmount = basePrice.multiply((discount.getPercentage()));
            product.setFinalPrice(basePrice.subtract(discountAmount));
            score += ((discount.getPercentage()) != null ? discount.getPercentage().multiply(BigDecimal.valueOf(50)).doubleValue() : 0);

        } else {
            product.setFinalPrice(basePrice);
        }
        score += product.getSalesCount() != null ? product.getSalesCount() * 0.5: 0;
        LocalDateTime createdAt = product.getCreatedAt(); // tipo LocalDateTime
        long daysSince = ChronoUnit.DAYS.between(createdAt, LocalDateTime.now());
        if (daysSince < 30) score += 10;
        if (daysSince < 7) score += 20;

        product.setRecommendedScore(score);

    }
}




package com.ecommerce.services;

import com.ecommerce.dto.DiscountDTO;
import com.ecommerce.entities.Discount;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductDiscount;
import com.ecommerce.repositories.DiscountRepository;
import com.ecommerce.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Discount create(DiscountDTO dto) {
        List<Product> products = productRepository.findAllById(dto.getProductIds());

        Discount discount = Discount.builder()
                .name(dto.getName())
                .percentage(dto.getPercentage())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .active(dto.getActive())
                .deleted(false)
                .build();

        List<ProductDiscount> relations = products.stream()
                .map(p -> new ProductDiscount(null, p, discount))
                .collect(Collectors.toList());

        discount.setProducts(relations);
        return discountRepository.save(discount);
    }

    public Optional<Discount> getById(Long id) {
        return discountRepository.findByIdAndDeletedFalse(id);
    }

    public List<Discount> getAll() {
        return discountRepository.findAllByDeletedFalse();
    }

    @Transactional
    public Discount update(Long id, DiscountDTO dto) {
        Discount existing = discountRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Descuento no encontrado"));

        existing.setName(dto.getName());
        existing.setPercentage(dto.getPercentage());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setActive(dto.getActive());

        List<Product> products = productRepository.findAllById(dto.getProductIds());
        List<ProductDiscount> relations = products.stream()
                .map(p -> new ProductDiscount(null, p, existing))
                .collect(Collectors.toList());

        existing.getProducts().clear();
        existing.getProducts().addAll(relations);

        return discountRepository.save(existing);
    }

    public void delete(Long id) {
        Discount existing = discountRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Descuento no encontrado"));
        existing.setDeleted(true);
        discountRepository.save(existing);
    }
}

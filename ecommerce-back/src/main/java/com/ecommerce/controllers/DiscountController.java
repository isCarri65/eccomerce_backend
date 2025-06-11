package com.ecommerce.controllers;

import com.ecommerce.dto.DiscountDTO;
import com.ecommerce.entities.Discount;
import com.ecommerce.services.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public Discount create(@RequestBody DiscountDTO dto) {
        return discountService.create(dto);
    }

    @GetMapping("/{id}")
    public Discount getById(@PathVariable Long id) {
        return discountService.getById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @GetMapping
    public List<Discount> getAll() {
        return discountService.getAll();
    }

    @PutMapping("/{id}")
    public Discount update(@PathVariable Long id, @RequestBody DiscountDTO dto) {
        return discountService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        discountService.delete(id);
    }
}

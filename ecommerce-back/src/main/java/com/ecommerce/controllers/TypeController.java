package com.ecommerce.controllers;

import com.ecommerce.entities.Type;
import com.ecommerce.services.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @PostMapping
    public Type create(@RequestBody Type type) {
        return typeService.create(type);
    }

    @GetMapping("/{id}")
    public Type getById(@PathVariable Long id) {
        return typeService.getById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @GetMapping
    public List<Type> getAll() {
        return typeService.getAll();
    }

    @PutMapping("/{id}")
    public Type update(@PathVariable Long id, @RequestBody Type type) {
        return typeService.update(id, type);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        typeService.delete(id);
    }
}

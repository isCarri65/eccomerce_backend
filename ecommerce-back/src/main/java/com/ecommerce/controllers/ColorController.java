package com.ecommerce.controllers;

import com.ecommerce.entities.Color;
import com.ecommerce.services.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @PostMapping
    public Color create(@RequestBody Color color) {
        return colorService.create(color);
    }

    @GetMapping("/{id}")
    public Color getById(@PathVariable Long id) {
        return colorService.getById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @GetMapping
    public List<Color> getAll() {
        return colorService.getAll();
    }

    @PutMapping("/{id}")
    public Color update(@PathVariable Long id, @RequestBody Color color) {
        return colorService.update(id, color);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        colorService.delete(id);
    }
}

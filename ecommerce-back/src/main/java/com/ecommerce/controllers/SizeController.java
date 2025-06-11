package com.ecommerce.controllers;

import com.ecommerce.entities.Size;
import com.ecommerce.services.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sizes")
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;

    @PostMapping
    public Size create(@RequestBody Size size) {
        return sizeService.create(size);
    }

    @GetMapping("/{id}")
    public Size getById(@PathVariable Long id) {
        return sizeService.getById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @GetMapping
    public List<Size> getAll() {
        return sizeService.getAll();
    }

    @PutMapping("/{id}")
    public Size update(@PathVariable Long id, @RequestBody Size size) {
        return sizeService.update(id, size);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sizeService.delete(id);
    }
}

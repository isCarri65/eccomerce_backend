
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.entities.Color;
import com.ecommerce.services.ColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/public/colors")
public class ColorPublicController {
    private final ColorService service;

    public ColorPublicController(ColorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<Color>> getAllActives() {
        return ResponseEntity.ok(service.getAllActives());
    }

    @PostMapping
    public ResponseEntity<Color> create(@RequestBody Color color) {
        return ResponseEntity.ok(service.create(color));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Color> update(@PathVariable Long id, @RequestBody Color color) {
        return ResponseEntity.ok(service.update(id, color));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") // Doble llave para escapar en format()
    public ResponseEntity<Color> getByIdActives(@PathVariable Long id) {
        return service.findByIdActive(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

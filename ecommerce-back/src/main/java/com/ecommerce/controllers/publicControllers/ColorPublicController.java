
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.entities.Color;
import com.ecommerce.services.ColorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}") // Doble llave para escapar en format()
    public ResponseEntity<Color> getByIdActives(@PathVariable Long id) {
        return service.findByIdActive(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

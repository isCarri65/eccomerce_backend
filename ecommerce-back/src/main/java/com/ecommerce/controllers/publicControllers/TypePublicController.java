
package com.ecommerce.controllers.publicControllers;

import com.ecommerce.entities.Type;
import com.ecommerce.services.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/public/types")
public class TypePublicController {
    private final TypeService service;

    public TypePublicController(TypeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<Type>> getAllActives() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            System.out.println("No hay autenticación activa");
        } else {
            System.out.println("Authorities: " + auth.getAuthorities());
        }
        return ResponseEntity.ok(service.getAllActives());
    }

    @GetMapping("/{id}") // Doble llave para escapar en format()
    public ResponseEntity<Type> getByIdActives(@PathVariable Long id) {
        return service.findByIdActive(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

package com.ecommerce.controllers.publicControllers;

import com.ecommerce.entities.Category;
import com.ecommerce.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/public/categories")
public class CategoryPublicController {
    private final CategoryService categoryService;

    public CategoryPublicController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<Set<Category>> getAllActives(){
        return ResponseEntity.ok(categoryService.getAllActives());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getByIdActives(@PathVariable Long id){
        return categoryService.findByIdActive(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}


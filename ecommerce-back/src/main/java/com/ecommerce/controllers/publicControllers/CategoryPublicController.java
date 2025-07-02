package com.ecommerce.controllers.publicControllers;

import com.ecommerce.dto.Category.CategoryAdminDTO;
import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.mappers.CategoryMapper;
import com.ecommerce.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/public/categories")
public class CategoryPublicController extends BasePublicController<Category, Long, CategoryDTO> {
    private final CategoryService categoryService;

    public CategoryPublicController(CategoryService categoryService, CategoryMapper mapper) {
        super(categoryService, mapper);
        this.categoryService = categoryService;
    }

    @GetMapping("/getAllByTypeId/{id}")
    public ResponseEntity<List<CategoryDTO>> getAllByTypeId(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getAllByTypeId(id));
    }
    @GetMapping("/byTag")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByTag(@RequestParam String tag) {
        List<CategoryDTO> result = categoryService.getCategoriesByTag(tag);
        return ResponseEntity.ok(result);
    }
}


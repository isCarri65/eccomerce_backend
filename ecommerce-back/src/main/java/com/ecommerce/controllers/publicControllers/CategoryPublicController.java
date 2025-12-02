package com.ecommerce.controllers.publicControllers;

import com.ecommerce.dto.Category.CategoryAdminDTO;
import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.mappers.CategoryMapper;
import com.ecommerce.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/public/categories")
public class CategoryPublicController extends BasePublicController<Category, Long, CategoryDTO> {
    private final CategoryService categoryService;

    public CategoryPublicController(CategoryService categoryService, CategoryMapper mapper) {
        super(categoryService, mapper);
        this.categoryService = categoryService;
    }
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.create(category));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        return ResponseEntity.ok(categoryService.update(id, category));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
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


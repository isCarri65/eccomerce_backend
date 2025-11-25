package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.Category.CategoryAdminDTO;
import com.ecommerce.dto.Category.CreateCategoryDTO;
import com.ecommerce.dto.Category.UpdateCategoryDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.mappers.CategoryAdminMapper;
import com.ecommerce.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController extends BaseController<Category, Long, CategoryAdminDTO, CreateCategoryDTO, UpdateCategoryDTO> {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService, CategoryAdminMapper mapper) {
        super(categoryService, mapper);
        this.categoryService = categoryService;
    }
    
    @PostMapping("/createWithImages")
    public ResponseEntity<CategoryAdminDTO> createCategoryWithImages(@ModelAttribute CreateCategoryDTO dto){
        return ResponseEntity.ok(categoryService.createCategoryWithImages(dto));
    }
    
    @PutMapping("/{id}/updateWithImages")
    public ResponseEntity<CategoryAdminDTO> updateCategoryWithImages(@PathVariable Long id, @ModelAttribute UpdateCategoryDTO dto){
        return ResponseEntity.ok(categoryService.updateCategoryWithImages(id, dto));
    }

}

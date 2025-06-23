package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.Category.CategoryAdminDTO;
import com.ecommerce.dto.Category.CreateCategoryDTO;
import com.ecommerce.dto.Category.UpdateCategoryDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.mappers.CategoryAdminMapper;
import com.ecommerce.services.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController extends BaseController<Category, Long, CategoryAdminDTO, CreateCategoryDTO, UpdateCategoryDTO> {

    public CategoryController(CategoryService categoryService, CategoryAdminMapper mapper) {
        super(categoryService, mapper);
    }


}

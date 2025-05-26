package com.ecommerce.controllers;

import com.ecommerce.entities.Category;
import com.ecommerce.services.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController extends BaseController<Category, Long> {
    public CategoryController(CategoryService categoryService) {
        super(categoryService);
    }
}

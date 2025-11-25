package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.CategoryGallery.CategoryGalleryAdminDTO;
import com.ecommerce.dto.CategoryGallery.CreateCategoryGalleryDTO;
import com.ecommerce.dto.CategoryGallery.UpdateCategoryGalleryDTO;
import com.ecommerce.entities.CategoryGallery;
import com.ecommerce.mappers.CategoryGalleryMapper;
import com.ecommerce.services.CategoryGalleryService;
import com.ecommerce.services.FileUploadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/categoryGalleries")
public class CategoryGalleryController extends BaseController<CategoryGallery, Long, CategoryGalleryAdminDTO, CreateCategoryGalleryDTO, UpdateCategoryGalleryDTO> {
    
    private final FileUploadService fileUploadService;
    private final CategoryGalleryService categoryGalleryService;
    
    public CategoryGalleryController(CategoryGalleryService categoryGalleryService, 
                                   FileUploadService fileUploadService, 
                                   CategoryGalleryMapper mapper) {
        super(categoryGalleryService, mapper);
        this.fileUploadService = fileUploadService;
        this.categoryGalleryService = categoryGalleryService;
    }
    
    // El método deleteImg ya está implementado en el controlador CategoryImageController
    // pero podemos agregarlo aquí también para consistencia
}

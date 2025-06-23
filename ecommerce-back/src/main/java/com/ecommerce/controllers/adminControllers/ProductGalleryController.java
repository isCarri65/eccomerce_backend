package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.ProductGallery.CreateProductGalleryDTO;
import com.ecommerce.dto.ProductGallery.ProductGalleryAdminDTO;
import com.ecommerce.dto.ProductGallery.UpdateProductGalleryDTO;
import com.ecommerce.entities.ProductGallery;
import com.ecommerce.mappers.ProductGalleryAdminMapper;
import com.ecommerce.services.FileUploadService;
import com.ecommerce.services.ProductGalleryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/productGalleries")
public class ProductGalleryController extends BaseController<ProductGallery, Long, ProductGalleryAdminDTO, CreateProductGalleryDTO, UpdateProductGalleryDTO> {
    private final FileUploadService fileUploadService;
    private final ProductGalleryService productGalleryService;

    public ProductGalleryController(ProductGalleryService productGalleryService, FileUploadService fileUploadService, ProductGalleryAdminMapper mapper) {
        super(productGalleryService, mapper);
        this.fileUploadService = fileUploadService;
        this.productGalleryService = productGalleryService;
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<Void> deleteImg(@PathVariable Long id, @RequestParam String publicId) {
        productGalleryService.reallyDelete(id);
        fileUploadService.deleteFile(publicId);
        return ResponseEntity.noContent().build();
    }
}

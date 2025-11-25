package com.ecommerce.controllers.publicControllers;

import com.ecommerce.dto.CategoryGallery.CategoryGalleryDTO;
import com.ecommerce.entities.CategoryGallery;
import com.ecommerce.mappers.CategoryGalleryMapper;
import com.ecommerce.services.CategoryGalleryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/categorygalleries")
public class CategoryGalleryPublicController {
    
    private final CategoryGalleryService service;
    private final CategoryGalleryMapper mapper;
    
    public CategoryGalleryPublicController(CategoryGalleryService service, CategoryGalleryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    
    @GetMapping
    public ResponseEntity<List<CategoryGalleryDTO>> getAllActives() {
        List<CategoryGallery> galleries = new ArrayList<>(service.getAllActives());
        List<CategoryGalleryDTO> dtos = galleries.stream()
                .map(mapper::toPublicDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoryGalleryDTO> getByIdActives(@PathVariable Long id) {
        return service.findByIdActive(id)
                .map(mapper::toPublicDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<CategoryGalleryDTO>> getByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<CategoryGallery> images = service.getAllByCategoryId(categoryId);
        if (images.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<CategoryGalleryDTO> dtos = images.stream()
                .map(mapper::toPublicDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }
    
    @GetMapping("/category/{categoryId}/main")
    public ResponseEntity<CategoryGalleryDTO> getMainImageByCategoryId(@PathVariable("categoryId") Long categoryId) {
        Optional<CategoryGallery> mainImageOpt = service.findMainImageByCategoryId(categoryId);
        if (mainImageOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toPublicDTO(mainImageOpt.get()));
    }
}

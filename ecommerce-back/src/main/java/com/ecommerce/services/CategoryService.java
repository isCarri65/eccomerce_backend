package com.ecommerce.services;

import com.ecommerce.dto.Category.CategoryAdminDTO;
import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.dto.Category.CreateCategoryDTO;
import com.ecommerce.dto.Category.UpdateCategoryDTO;
import com.ecommerce.dto.FileUploadResponse;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.CategoryGallery;
import com.ecommerce.mappers.CategoryAdminMapper;
import com.ecommerce.mappers.CategoryMapper;
import com.ecommerce.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService extends BaseService<Category, Long>{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryAdminMapper categoryAdminMapper;
    private final CategoryGalleryService categoryGalleryService;
    private final FileUploadService fileUploadService;
    
    public CategoryService(CategoryRepository categoryRepository, 
                         CategoryMapper categoryMapper,
                         CategoryAdminMapper categoryAdminMapper,
                         CategoryGalleryService categoryGalleryService,
                         FileUploadService fileUploadService) {
        super(categoryRepository);
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
        this.categoryAdminMapper = categoryAdminMapper;
        this.categoryGalleryService = categoryGalleryService;
        this.fileUploadService = fileUploadService;
    }

    public List<CategoryDTO> getAllByTypeId(Long typeId){
        return categoryRepository.findByTypeIdAndDeleted(typeId, false).stream().map(categoryMapper::toDTO).collect(Collectors.toList());
    }

    public List<CategoryDTO> getCategoriesByTag(String tag) {

        List<Category> categories = categoryRepository.findByTag(tag);
        return categories.stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Set<Category> findAllByListId(Set<Long> ids){
        return categoryRepository.getAllByIdIn(ids);
    }
    
    @Transactional
    public CategoryAdminDTO createCategoryWithImages(CreateCategoryDTO dto) {
        // Crear la categoría primero
        Category category = categoryAdminMapper.CDTOtoEntity(dto);
        Category savedCategory = baseRepository.save(category);
        
        // Procesar imágenes si existen
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            processCategoryImages(savedCategory.getId(), dto.getImages(), dto.getImageNames(), dto.getIsMainFlags());
        }
        
        return categoryAdminMapper.toDTO(savedCategory);
    }
    
    @Transactional
    public CategoryAdminDTO updateCategoryWithImages(Long id, UpdateCategoryDTO dto) {
        Category category = baseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));
        
        // Actualizar la categoría
        categoryAdminMapper.UDTOtoEntity(dto, category);
        Category savedCategory = baseRepository.save(category);
        
        // Eliminar imágenes marcadas para eliminación
        if (dto.getImagesToDelete() != null && !dto.getImagesToDelete().isEmpty()) {
            deleteCategoryImages(dto.getImagesToDelete());
        }
        
        // Procesar nuevas imágenes si existen
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            processCategoryImages(savedCategory.getId(), dto.getImages(), dto.getImageNames(), dto.getIsMainFlags());
        }
        
        return categoryAdminMapper.toDTO(savedCategory);
    }
    
    private void processCategoryImages(Long categoryId, List<MultipartFile> images, List<String> imageNames, List<Boolean> isMainFlags) {
        for (int i = 0; i < images.size(); i++) {
            MultipartFile image = images.get(i);
            String imageName = (imageNames != null && i < imageNames.size()) ? imageNames.get(i) : image.getOriginalFilename();
            Boolean isMain = (isMainFlags != null && i < isMainFlags.size()) ? isMainFlags.get(i) : false;
            
            // Subir imagen a Cloudinary
            FileUploadResponse uploadResponse = fileUploadService.uploadFile(image, "categories");
            
            // Crear CategoryGallery
            CategoryGallery gallery = CategoryGallery.builder()
                    .imageUrl(uploadResponse.getImageUrl())
                    .publicId(uploadResponse.getPublicId())
                    .name(imageName)
                    .isMain(isMain)
                    .category(categoryRepository.findById(categoryId).orElseThrow())
                    .build();
            
            // Si es imagen principal, desmarcar las otras
            if (isMain) {
                categoryGalleryService.setAsMainImage(categoryId, categoryId);
            }
            
            categoryGalleryService.create(gallery);
        }
    }
    
    private void deleteCategoryImages(List<Long> imageIds) {
        for (Long imageId : imageIds) {
            CategoryGallery gallery = categoryGalleryService.findById(imageId);
            if (gallery != null) {
                // Eliminar de Cloudinary
                fileUploadService.deleteFile(gallery.getPublicId());
                // Eliminar de base de datos
                categoryGalleryService.reallyDelete(imageId);
            }
        }
    }
}

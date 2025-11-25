package com.ecommerce.services;

import com.ecommerce.dto.ProductGallery.CreateProductGalleryDTO;
import com.ecommerce.dto.ProductGallery.ProductGalleryDTO;
import com.ecommerce.entities.ProductGallery;
import com.ecommerce.mappers.ProductGalleryAdminMapper;
import com.ecommerce.mappers.ProductGalleryMapper;
import com.ecommerce.repositories.ProductGalleryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductGalleryService extends BaseService<ProductGallery, Long> {
    private final ProductGalleryRepository productGalleryRepository;
    private final ProductGalleryMapper productGalleryMapper;
    private final ProductGalleryAdminMapper productGalleryAdminMapper;
    public ProductGalleryService(ProductGalleryRepository galleryProductRepository, ProductGalleryMapper productGalleryMapper, ProductGalleryAdminMapper productGalleryAdminMapper) {
        super(galleryProductRepository);
        this.productGalleryRepository = galleryProductRepository;
        this.productGalleryMapper = productGalleryMapper;
        this.productGalleryAdminMapper = productGalleryAdminMapper;
    }
    public List<ProductGallery> getAllByProductId(Long productId) {
       return productGalleryRepository.findByProductId(productId);

    }
    public Optional<ProductGallery> findProductGalleryMainByProductId(Long productId) {
        return  productGalleryRepository.findByProduct_IdAndIsMain(productId, true);
    }

    public List<ProductGallery> createEntitiesWhitDTOS(List<CreateProductGalleryDTO> productGalleryDTOS) {

        Set<ProductGallery> productGalleries =  productGalleryDTOS.stream().map(productGalleryAdminMapper::CDTOtoEntity).collect(Collectors.toSet());
        return productGalleryRepository.saveAll(productGalleries);
    }

    public Set<ProductGalleryDTO> listEntityToDTO(List<ProductGallery> productGalleries) {
        return productGalleries.stream().map(productGalleryMapper::toDTO).collect(Collectors.toSet());
    }
    
    public void setAsMainImage(Long galleryId, Long productId) {
        // Primero desmarcar todas las imágenes principales del producto
        List<ProductGallery> allImages = getAllByProductId(productId);
        allImages.forEach(img -> img.setIsMain(false));
        productGalleryRepository.saveAll(allImages);
        
        // Marcar la imagen seleccionada como principal
        Optional<ProductGallery> mainImage = findByIdActive(galleryId);
        if (mainImage.isPresent()) {
            mainImage.get().setIsMain(true);
            update(galleryId, mainImage.get());
        }
    }

    @Transactional
    public void softDeleteImage(Long productId, Long imageId) {

        ProductGallery image = productGalleryRepository
                .findByIdAndProductId(imageId, productId)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        image.setDeleted(true);

        productGalleryRepository.save(image);
    }


}
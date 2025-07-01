package com.ecommerce.mappers;

import com.ecommerce.dto.ProductGallery.CreateProductGalleryDTO;
import com.ecommerce.dto.ProductGallery.ProductGalleryDTO;
import com.ecommerce.entities.ProductGallery;
import org.springframework.stereotype.Component;

@Component
public class ProductGalleryMapper implements BaseMapper<ProductGallery, ProductGalleryDTO> {
    public ProductGalleryDTO toDTO(ProductGallery productGallery) {
        ProductGalleryDTO dto = new ProductGalleryDTO();
        dto.setId(productGallery.getId());
        dto.setProductId(productGallery.getProduct().getId());
        dto.setImageUrl(productGallery.getImageUrl());
        dto.setIsMain(productGallery.getIsMain());
        return dto;
    }

}

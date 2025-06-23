package com.ecommerce.mappers;

import com.ecommerce.dto.ProductGallery.CreateProductGalleryDTO;
import com.ecommerce.dto.ProductGallery.ProductGalleryAdminDTO;
import com.ecommerce.dto.ProductGallery.UpdateProductGalleryDTO;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductGallery;
import org.springframework.stereotype.Component;

@Component
public class ProductGalleryAdminMapper implements BaseAdminMapper<ProductGallery, Long, ProductGalleryAdminDTO, CreateProductGalleryDTO, UpdateProductGalleryDTO> {
    public ProductGalleryAdminDTO toDTO(ProductGallery productGallery) {
        ProductGalleryAdminDTO dto = new ProductGalleryAdminDTO();
        dto.setPublicId(productGallery.getPublicId());
        dto.setId(productGallery.getId());
        dto.setProductId(productGallery.getProduct().getId());
        dto.setImageUrl(productGallery.getImageUrl());
        dto.setIsMain(productGallery.getIsMain());
        dto.setDeleted(productGallery.isDeleted());
        return dto;
    }

    public ProductGallery CDTOtoEntity(CreateProductGalleryDTO productGalleryDTO) {
        Product product = new Product();
        product.setId(productGalleryDTO.getProductId());

        ProductGallery productGallery = new ProductGallery();
        productGallery.setPublicId(productGalleryDTO.getPublicId());
        productGallery.setProduct(product);
        productGallery.setImageUrl(productGalleryDTO.getImageUrl());
        productGallery.setIsMain(productGalleryDTO.getIsMain());
        return productGallery;
    }

    public ProductGallery UDTOtoEntity(UpdateProductGalleryDTO productGalleryDTO, Long id) {
        ProductGallery productGallery = new ProductGallery();
        productGallery.setId(id);
        productGallery.setImageUrl(productGalleryDTO.getImageUrl());
        productGallery.setIsMain(productGalleryDTO.getIsMain());
        productGallery.setDeleted(productGalleryDTO.isDeleted());
        productGallery.setPublicId(productGalleryDTO.getPublicId());
        return productGallery;
    }
}

package com.ecommerce.mappers;

import com.ecommerce.dto.productVariant.ProductVariantAdminDTO;
import com.ecommerce.dto.productVariant.ProductVariantDTO;
import com.ecommerce.entities.ProductVariant;
import org.springframework.stereotype.Component;

@Component
public class ProductVariantMapper implements BaseMapper<ProductVariant ,ProductVariantDTO> {
    @Override
    public ProductVariantDTO toDTO(ProductVariant productVariant) {

        ProductVariantDTO dto = new ProductVariantDTO();
        dto.setId(productVariant.getId());
        dto.setQuantity(productVariant.getQuantity());
        dto.setState(productVariant.getState());

        dto.setProductId(productVariant.getProduct().getId());
        dto.setColorId(productVariant.getColor().getId());
        dto.setSizeId(productVariant.getSize().getId());


        return dto;
    }
}

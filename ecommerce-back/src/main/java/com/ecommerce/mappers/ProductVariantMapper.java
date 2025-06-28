package com.ecommerce.mappers;

import com.ecommerce.dto.Color.ColorDTO;
import com.ecommerce.dto.Size.SizeDTO;
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

        ColorDTO colorDTO = new ColorDTO();
        colorDTO.setId(productVariant.getColor().getId());
        colorDTO.setName(productVariant.getColor().getName());
        dto.setColor(colorDTO);

        SizeDTO sizeDTO = new SizeDTO();
        sizeDTO.setId(productVariant.getSize().getId());
        sizeDTO.setName(productVariant.getSize().getValue());
        sizeDTO.setSizeType(String.valueOf(productVariant.getSize().getSizeType()));
        dto.setSize(sizeDTO);

        return dto;
    }
}

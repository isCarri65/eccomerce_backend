package com.ecommerce.mappers;

import com.ecommerce.dto.productVariant.CreateProductVariantDTO;
import com.ecommerce.dto.productVariant.ProductVariantAdminDTO;
import com.ecommerce.dto.productVariant.UpdateProductVariantDTO;
import com.ecommerce.entities.Color;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductVariant;
import com.ecommerce.entities.Size;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductVariantAdminMapper implements BaseAdminMapper<ProductVariant, Long, ProductVariantAdminDTO, CreateProductVariantDTO, UpdateProductVariantDTO> {
    @Override
    public ProductVariantAdminDTO toDTO(ProductVariant productVariant) {
        
        ProductVariantAdminDTO dto = new ProductVariantAdminDTO();
        dto.setDeleted(productVariant.isDeleted());
        dto.setId(productVariant.getId());
        dto.setQuantity(productVariant.getQuantity());
        dto.setState(productVariant.getState());

        dto.setProductId(productVariant.getProduct().getId());
        dto.setColorId(productVariant.getColor().getId());
        dto.setSizeId(productVariant.getSize().getId());


        return dto;

    }
    @Override
    public ProductVariant UDTOtoEntity (UpdateProductVariantDTO updateDTO, Long id){
        ProductVariant productVariant = new ProductVariant();
        productVariant.setId(id);
        productVariant.setQuantity(updateDTO.getQuantity());
        productVariant.setState(updateDTO.getState());
        productVariant.setDeleted(updateDTO.isDeleted());

        return getProductVariant(productVariant, updateDTO.getProductId(), updateDTO.getSizeId(), updateDTO.getColorId());
    }
    @Override
    public ProductVariant CDTOtoEntity(CreateProductVariantDTO createDTO) {
        ProductVariant productVariant = new ProductVariant();
        productVariant.setQuantity(createDTO.getQuantity());
        productVariant.setState(createDTO.getState());

        return getProductVariant(productVariant, createDTO.getProductId(), createDTO.getSizeId(), createDTO.getColorId());
    }

    private ProductVariant getProductVariant(ProductVariant productVariant, Long productId, Long sizeId, Long colorId) {
        Product product = new Product();
        product.setId(productId);
        productVariant.setProduct(product);

        Size size = new Size();
        size.setId(sizeId);
        productVariant.setSize(size);

        Color color = new Color();
        color.setId(colorId);
        productVariant.setColor(color);
        return productVariant;
    }
}

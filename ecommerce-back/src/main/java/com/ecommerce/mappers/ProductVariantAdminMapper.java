package com.ecommerce.mappers;

import com.ecommerce.dto.Color.ColorDTO;
import com.ecommerce.dto.Size.SizeDTO;
import com.ecommerce.dto.productVariant.CreateProductVariantDTO;
import com.ecommerce.dto.productVariant.ProductVariantAdminDTO;
import com.ecommerce.dto.productVariant.UpdateProductVariantDTO;
import com.ecommerce.entities.Color;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductVariant;
import com.ecommerce.entities.Size;
import org.springframework.stereotype.Component;


@Component
public class ProductVariantAdminMapper implements BaseAdminMapper<ProductVariant, ProductVariantAdminDTO, CreateProductVariantDTO, UpdateProductVariantDTO> {
    @Override
    public ProductVariantAdminDTO toDTO(ProductVariant productVariant) {

        ProductVariantAdminDTO dto = new ProductVariantAdminDTO();
        dto.setDeleted(productVariant.isDeleted());
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
    @Override
    public void UDTOtoEntity (UpdateProductVariantDTO updateDTO, ProductVariant productVariant) {
        productVariant.setQuantity(updateDTO.getQuantity());
        productVariant.setState(updateDTO.getState());
        productVariant.setDeleted(updateDTO.isDeleted());

        updateProductVariant(productVariant, updateDTO.getProductId(), updateDTO.getSizeId(), updateDTO.getColorId());
    }
    @Override
    public ProductVariant CDTOtoEntity(CreateProductVariantDTO createDTO) {
        ProductVariant productVariant = new ProductVariant();
        productVariant.setQuantity(createDTO.getQuantity());
        productVariant.setState(createDTO.getState());
        return productVariant;
    }

    private void updateProductVariant(ProductVariant productVariant, Long productId, Long sizeId, Long colorId) {
        Product product = new Product();
        product.setId(productId);
        productVariant.setProduct(product);

        Size size = new Size();
        size.setId(sizeId);
        productVariant.setSize(size);

        Color color = new Color();
        color.setId(colorId);
        productVariant.setColor(color);
    }
}

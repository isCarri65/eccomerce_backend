package com.ecommerce.mappers;

import com.ecommerce.dto.ProductDiscount.ProductDiscountAdminDTO;
import com.ecommerce.dto.ProductDiscount.CreateProductDiscountDTO;
import com.ecommerce.dto.ProductDiscount.UpdateProductDiscountDTO;
import com.ecommerce.entities.DiscountRule;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductDiscount;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductDiscountAdminMapper implements  BaseAdminMapper<ProductDiscount, Long, ProductDiscountAdminDTO, CreateProductDiscountDTO, UpdateProductDiscountDTO> {
    public ProductDiscountAdminDTO toDTO(ProductDiscount productDiscount) {
        DiscountMapper discountMapper = new DiscountMapper();
        ProductDiscountAdminDTO dto = new ProductDiscountAdminDTO();
        dto.setDeleted(productDiscount.isDeleted());
        dto.setId(productDiscount.getId());
        dto.setProductId(productDiscount.getProduct().getId());
        dto.setState(productDiscount.isState());
        dto.setDiscount(discountMapper.toDTO(productDiscount.getDiscount()));

        return dto;

    }
    @Override
    public ProductDiscount UDTOtoEntity (UpdateProductDiscountDTO updateDTO, Long id){
        ProductDiscount productDiscount = new ProductDiscount();
        productDiscount.setId(id);
        productDiscount.setDeleted(updateDTO.isDeleted());
        productDiscount.setState(updateDTO.isState());

        return getProductDiscount(productDiscount, updateDTO.getDiscountId(), updateDTO.getProductId());
    }


    @Override
    public ProductDiscount CDTOtoEntity(CreateProductDiscountDTO createDTO) {
        ProductDiscount productDiscount = new ProductDiscount();


        return getProductDiscount(productDiscount, createDTO.getDiscountId(), createDTO.getProductId());
    }


    private ProductDiscount getProductDiscount(ProductDiscount productDiscount, Long discountId, Long productId) {
        DiscountRule discount = new DiscountRule();
        discount.setId(discountId);
        productDiscount.setDiscount(discount);

        Product product = new Product();
        product.setId(productId);
        productDiscount.setProduct(product);
        return productDiscount;
    }
}

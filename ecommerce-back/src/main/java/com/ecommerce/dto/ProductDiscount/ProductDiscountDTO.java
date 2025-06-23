package com.ecommerce.dto.ProductDiscount;

import com.ecommerce.dto.Product.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDiscountDTO  {
    private Long id;
    private boolean state;
    private ProductDTO product;
    private Long discountId;
}
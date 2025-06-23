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
public class CreateProductDiscountDTO {
    private Long productId;
    private Long discountId;
}

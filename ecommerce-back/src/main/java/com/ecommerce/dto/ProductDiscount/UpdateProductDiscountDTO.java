package com.ecommerce.dto.ProductDiscount;

import com.ecommerce.dto.Product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDiscountDTO {
    private boolean state;
    private Long productId;
    private Long discountId;
    private boolean deleted;
}

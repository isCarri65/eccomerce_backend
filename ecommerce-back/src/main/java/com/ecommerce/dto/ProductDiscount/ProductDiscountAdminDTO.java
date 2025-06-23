package com.ecommerce.dto.ProductDiscount;


import com.ecommerce.dto.Discount.DiscountDTO;
import com.ecommerce.dto.Product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDiscountAdminDTO  {
    private Long id;
    private boolean state;
    private Long productId;
    private DiscountDTO discount;
    private boolean deleted;
}
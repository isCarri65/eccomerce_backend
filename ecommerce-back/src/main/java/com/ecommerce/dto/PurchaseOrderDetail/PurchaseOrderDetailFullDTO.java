package com.ecommerce.dto.PurchaseOrderDetail;

import com.ecommerce.dto.Product.ProductDTO;
import com.ecommerce.dto.productVariant.ProductVariantDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDetailFullDTO {
    private Long id;
    private Long orderId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private ProductVariantDTO productVariantDTO;
    private ProductDTO productDTO;
    private Long discountId;
}

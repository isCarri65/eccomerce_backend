package com.ecommerce.dto.PurchaseOrderDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDetailDTO {
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
    private Long productVariantId;
    private Long discountId;
}

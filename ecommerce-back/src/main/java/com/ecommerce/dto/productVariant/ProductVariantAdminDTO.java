package com.ecommerce.dto.productVariant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariantAdminDTO {
    private Long id;
    private Integer quantity;
    private Boolean state;
    private boolean deleted;
    private Long productId;
    private Long sizeId;
    private Long colorId;
}

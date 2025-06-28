package com.ecommerce.dto.productVariant;

import com.ecommerce.dto.Color.ColorDTO;
import com.ecommerce.dto.Size.SizeDTO;
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
    private SizeDTO size;
    private ColorDTO color;
}

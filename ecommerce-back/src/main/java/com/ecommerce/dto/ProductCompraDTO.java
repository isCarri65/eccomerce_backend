package com.ecommerce.dto;

import lombok.Data;

@Data
public class ProductCompraDTO {
    private Long variantId;
    private Long discountId; // puede ser null
}

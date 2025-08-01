package com.ecommerce.dto.productVariant;

import com.ecommerce.dto.Color.ColorDTO;
import com.ecommerce.dto.Product.ProductListDTO;
import com.ecommerce.dto.Size.SizeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariantCartDTO {
        private Long id;
        private Integer stock;
        private Boolean state;
        private ProductListDTO productList;
        private SizeDTO size;
        private ColorDTO color;
}

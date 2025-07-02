package com.ecommerce.dto.Product;

// ProductUpdateDTO.java
import com.ecommerce.dto.ProductGallery.ProductGalleryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateProductDTO {
    private String name;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private String description;
    private Boolean state;
    private boolean deleted;
    private String genre;
    private List<Long> categoryIds;
}

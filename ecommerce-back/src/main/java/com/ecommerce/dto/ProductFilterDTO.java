package com.ecommerce.dto;

import com.ecommerce.entities.ProductGenreENUM;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductFilterDTO {
    private ProductGenreENUM genre;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Long sizeId;
    private Long colorId;
    private List<Long> categoryIds;
    private Long typeId;
}

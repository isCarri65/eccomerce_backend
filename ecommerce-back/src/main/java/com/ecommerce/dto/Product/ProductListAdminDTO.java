package com.ecommerce.dto.Product;

import com.ecommerce.dto.Category.CategoryDTO;

import java.math.BigDecimal;
import java.util.Set;

public class ProductListAdminDTO {
    private Long id;
    private String name;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private String description;
    private Boolean state;
    private String genre;
}

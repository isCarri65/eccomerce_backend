package com.ecommerce.dto.Product;

// ProductDTO.java (for frontend)
import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.entities.ProductGallery;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductListDTO {
    private Long id;
    private String name;
    private String description;
    private String genre;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private BigDecimal discountPercentage;
    private boolean stockAvailable;
    private Set<CategoryDTO> categories; // Full category objects for the frontend
    private String imageUrl;
}

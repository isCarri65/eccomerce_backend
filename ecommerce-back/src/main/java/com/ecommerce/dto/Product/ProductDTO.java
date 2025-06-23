package com.ecommerce.dto.Product;

// ProductDTO.java (for frontend)
import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.dto.ProductGallery.ProductGalleryDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String genre;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private Double discountPercentage;
    private boolean stockAvailable;
    private Set<CategoryDTO> categories; // Full category objects for the frontend
    private Set<ProductGalleryDTO> productGalleries;

}

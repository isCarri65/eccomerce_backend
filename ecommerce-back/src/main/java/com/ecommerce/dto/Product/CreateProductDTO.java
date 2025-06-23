package com.ecommerce.dto.Product;

// ProductCreateDTO.java
import com.ecommerce.dto.ProductGallery.CreateProductGalleryDTO;
import com.ecommerce.dto.ProductGallery.ProductGalleryDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
public class CreateProductDTO {
    private String name;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private String description;
    private Boolean state;
    private String genre; // Enum as String
    private Set<Long> categories; // IDs of categories
    private List<CreateProductGalleryDTO> productGalleries;
}

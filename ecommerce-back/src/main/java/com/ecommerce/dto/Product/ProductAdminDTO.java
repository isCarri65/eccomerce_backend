package com.ecommerce.dto.Product;

import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.dto.ProductGallery.ProductGalleryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAdminDTO {
    private Long id;
    private String name;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private String description;
    private Boolean state;
    private String genre;
    private Set<CategoryDTO> categories;
    private Set<ProductGalleryDTO> productGalleries;
    private boolean deleted;
}

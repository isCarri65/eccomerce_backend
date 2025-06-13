package com.ecommerce.dto.Product;

// ProductDTO.java (for frontend)
import com.ecommerce.dto.Category.CategoryDTO;
import lombok.Data;
import java.util.Set;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Double buyPrice;
    private Double sellPrice;
    private String description;
    private Boolean state;
    private String genre;
    private Set<CategoryDTO> categories; // Full category objects for the frontend
}

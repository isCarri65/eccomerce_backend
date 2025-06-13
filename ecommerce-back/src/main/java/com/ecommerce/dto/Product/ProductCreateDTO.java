package com.ecommerce.dto.Product;

// ProductCreateDTO.java
import lombok.Data;
import java.util.Set;

@Data
public class ProductCreateDTO {
    private String name;
    private Double buyPrice;
    private Double sellPrice;
    private String description;
    private Boolean state;
    private String genre; // Enum as String
    private Set<Long> categories; // IDs of categories
}

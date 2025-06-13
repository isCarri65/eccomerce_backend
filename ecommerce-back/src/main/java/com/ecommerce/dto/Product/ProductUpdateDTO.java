package com.ecommerce.dto.Product;

// ProductUpdateDTO.java
import lombok.Data;
import java.util.Set;

@Data
public class ProductUpdateDTO {
    private Long id; // ID required to identify which product to update
    private String name;
    private Double buyPrice;
    private Double sellPrice;
    private String description;
    private Boolean state;
    private String genre;
    private Set<Long> categories;
}

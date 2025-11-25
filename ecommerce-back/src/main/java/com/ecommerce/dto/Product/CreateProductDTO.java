package com.ecommerce.dto.Product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateProductDTO {
    private String name;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private String description;
    private Boolean state;
    private String genre; // Enum as String
    private List<Long> categories; // IDs of categories
    
    // Imágenes del producto
    private List<MultipartFile> images;
    private List<String> imageNames; // Nombres opcionales para las imágenes
    private List<Boolean> isMainFlags; // Flags para indicar cuál es la imagen principal
}

package com.ecommerce.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateProductDTO {
    private String name;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private String description;
    private Boolean state;
    private boolean deleted;
    private String genre;
    private List<Long> categoryIds;
    
    // Imágenes del producto
    private List<MultipartFile> images;
    private List<String> imageNames; // Nombres opcionales para las imágenes
    private List<Boolean> isMainFlags; // Flags para indicar cuál es la imagen principal
    private List<Long> imagesToDelete; // IDs de imágenes a eliminar
}

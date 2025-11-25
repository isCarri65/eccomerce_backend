package com.ecommerce.dto.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCategoryDTO {
    private String name;
    private boolean deleted;
    private List<String> tags;
    private Long typeId;
    
    // Imágenes de la categoría
    private List<MultipartFile> images;
    private List<String> imageNames; // Nombres opcionales para las imágenes
    private List<Boolean> isMainFlags; // Flags para indicar cuál es la imagen principal
    private List<Long> imagesToDelete; // IDs de imágenes a eliminar
    
    // Para compatibilidad con el sistema existente
    private String imageUrl; // Imagen principal (legacy)
    private String publicId; // Public ID de la imagen principal (legacy)
}

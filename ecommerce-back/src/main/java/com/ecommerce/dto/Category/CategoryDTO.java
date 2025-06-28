package com.ecommerce.dto.Category;
// CategoryDTO.java (minimal to avoid cyclic reference)
import com.ecommerce.dto.Type.TypeDTO;
import com.ecommerce.entities.Type;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
    private String imageUrl;

    private TypeDTO type;
}

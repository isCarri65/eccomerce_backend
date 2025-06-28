package com.ecommerce.dto.Category;

import com.ecommerce.dto.Type.TypeAdminDTO;
import com.ecommerce.dto.Type.TypeDTO;
import com.ecommerce.entities.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryAdminDTO {

    private Long id;
    private String name;
    private String imageUrl;
    private String publicId;
    private boolean deleted;
    private TypeDTO type;
}

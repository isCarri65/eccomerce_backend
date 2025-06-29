package com.ecommerce.mappers;

import com.ecommerce.dto.Type.TypeAdminDTO;
import com.ecommerce.dto.Type.TypeDTO;
import com.ecommerce.entities.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper implements BaseMapper<Type, TypeDTO> {
    public TypeDTO toDTO(Type address) {
        TypeDTO dto = new TypeDTO();
        dto.setId(address.getId());
        dto.setName(address.getName());
        return dto;

    }
}

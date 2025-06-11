package com.ecommerce.services;

import com.ecommerce.dto.TypeDTO;
import com.ecommerce.entities.Type;
import com.ecommerce.repositories.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeService extends BaseService<Type, Long> {

    private final TypeRepository typeRepository;

    public TypeDTO createType(TypeDTO dto) {
        if (typeRepository.existsByName(dto.name())) {
            throw new RuntimeException("El tipo ya existe");
        }

        Type type = Type.builder().name(dto.name()).build();
        return toDTO(typeRepository.save(type));
    }

    public List<TypeDTO> getAllTypes() {
        return typeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private TypeDTO toDTO(Type type) {
        return new TypeDTO(type.getId(), type.getName());
    }
}

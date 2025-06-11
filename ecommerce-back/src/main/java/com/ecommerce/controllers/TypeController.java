package com.ecommerce.controllers;

import com.ecommerce.dto.TypeDTO;
import com.ecommerce.entities.Base;
import com.ecommerce.entities.Type;
import com.ecommerce.services.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class TypeController extends BaseController<Type, Long> {
    private final TypeService typeService;

    @PostMapping
    public TypeDTO createType(@RequestBody TypeDTO dto) {
        return typeService.createType(dto);
    }

    @GetMapping
    public List<TypeDTO> getAllTypes() {
        return typeService.getAllTypes();
    }
}

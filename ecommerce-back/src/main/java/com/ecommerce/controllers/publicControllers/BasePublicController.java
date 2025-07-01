package com.ecommerce.controllers.publicControllers;

import com.ecommerce.entities.Base;
import com.ecommerce.mappers.BaseMapper;
import com.ecommerce.services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public abstract class BasePublicController<
        E extends Base,
        ID extends Serializable,
        EntityDTO> {

    protected final BaseService<E, ID> service;
    protected final BaseMapper<E, EntityDTO> mapper;

    public BasePublicController(BaseService<E, ID> service, BaseMapper<E, EntityDTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping
    public ResponseEntity<Set<EntityDTO>> getAll() {
        return ResponseEntity.ok(service.getAllActives(mapper::toDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityDTO> findById(@PathVariable ID id) {
        return ResponseEntity.ok(service.findByIdActive(id, mapper::toDTO));
    }
}


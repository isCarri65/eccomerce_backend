package com.ecommerce.controllers.adminControllers;

import com.ecommerce.entities.Base;
import com.ecommerce.mappers.BaseAdminMapper;
import com.ecommerce.services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class BaseController<
        E extends Base,
        ID extends Serializable,
        EntityDTO,
        CreateDTO,
        UpdateDTO> {

    protected final BaseService<E, ID> service;
    protected final BaseAdminMapper<E, ID, EntityDTO, CreateDTO, UpdateDTO> mapper;

    public BaseController(BaseService<E, ID> service, BaseAdminMapper<E, ID, EntityDTO, CreateDTO, UpdateDTO> mapper){
        this.service = service;
        this.mapper = mapper;
    }


    @GetMapping
    public ResponseEntity<List<EntityDTO>> getAll(){
        return ResponseEntity.ok(service.getAll(mapper::toDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityDTO> findById(@PathVariable ID id){
        return ResponseEntity.ok(service.findById(id, mapper::toDTO));
    }

    @PostMapping
    public ResponseEntity<EntityDTO> create(@RequestBody CreateDTO dto) {
        return ResponseEntity.ok(service.create(dto, mapper));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityDTO> update(@PathVariable ID id, @RequestBody UpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto, mapper));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

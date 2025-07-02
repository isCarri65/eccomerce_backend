package com.ecommerce.controllers.adminControllers;


import com.ecommerce.entities.Base;
import com.ecommerce.mappers.BaseAdminMapper;
import com.ecommerce.services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.util.List;

public abstract class BaseSimpleController<
        E extends Base,
        ID extends Serializable> {

    protected final BaseService<E, ID> service;

    public BaseSimpleController(BaseService<E, ID> service){
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<E>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> findById(@PathVariable ID id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<E> create(@RequestBody E entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<E> update(@PathVariable ID id, @RequestBody E entity) {
        return ResponseEntity.ok(service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

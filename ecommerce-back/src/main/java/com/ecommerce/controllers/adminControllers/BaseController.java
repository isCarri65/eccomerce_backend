package com.ecommerce.controllers;

import com.ecommerce.entities.Base;
import com.ecommerce.services.BaseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public abstract class BaseController<E extends Base, ID extends Serializable> {

    protected final BaseService<E, ID> service;

    public BaseController(BaseService<E, ID> service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<E>> getAll() throws Exception {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> findById(@PathVariable ID id) throws Exception {
        System.out.println("id: " + id);
        E entity = service.findById(id);
        return entity != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }


    public ResponseEntity<Set<E>> getAllActives(){
        return ResponseEntity.ok(service.getAllActives());

    }

    @PostMapping
    public ResponseEntity<E> create(@RequestBody E entity) {
        return ResponseEntity.ok(service.create(entity));
    }

    @PutMapping
    public ResponseEntity<E> update(@RequestBody E entity) throws Exception {
        return ResponseEntity.ok(service.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ID id) {
        System.out.println("Entrando al delete");
        try {
            service.delete(id);
            return ResponseEntity.ok("Eliminado correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el recurso con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el recurso.");
        }
    }
}

package com.ecommerce.services;

import com.ecommerce.entities.Base;
import com.ecommerce.entities.Product;
import com.ecommerce.repositories.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class BaseService<E extends Base, ID extends Serializable> {

    protected final BaseRepository<E, ID> baseRepository;

    public BaseService(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public List<E> getAll() {
        return baseRepository.findAll();
    }


    public Set<E> getAllActives() {
        return baseRepository.getAllByDeleted(false);
    }
    public Set<E> getAllDeleted() {
        return baseRepository.getAllByDeleted(true);
    }

    public Optional<E> findById(ID id) {
        return baseRepository.findById(id);
    }
    public Optional<E> findByIdActive(ID id) {
        return baseRepository.findByIdAndDeleted(id, false);
    }

    @Transactional
    public E create(E entity) {
        return baseRepository.save(entity);
    }

    public E update(ID id, E entity) throws Exception {
        if (!baseRepository.existsById(id)) {
            throw new EntityNotFoundException("Entidad no encontrada para actualizar");
        }
        return baseRepository.save(entity);
    }
    @Transactional
    public void delete(ID id) {
        Optional<E> optionalEntity = baseRepository.findById(id);
        if (optionalEntity.isPresent()) {
            E entity = optionalEntity.get();
            entity.setDeleted(true);
            baseRepository.save(entity); // actualiza el estado en lugar de eliminarlo
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con id: " + id);
        }
    }

    public void reallyDelete(ID id) {
        Optional<E> optionalEntity = baseRepository.findById(id);
        if (optionalEntity.isPresent()) {
            E entity = optionalEntity.get();
            baseRepository.delete(entity); // elimina totalmente
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con id: " + id);
        }
    }

}
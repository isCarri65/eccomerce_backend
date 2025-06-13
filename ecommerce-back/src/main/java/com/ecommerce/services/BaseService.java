package com.ecommerce.services;

import com.ecommerce.entities.Base;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    public List<E> getAll() throws Exception {
        try {
            return baseRepository.findAll();
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public Set<E> getAllActives() {
        return baseRepository.getAllByDeleted(false);
    }

    @Transactional
    public E findById(ID id) throws Exception {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public E create(E entity) {
        return baseRepository.save(entity);
    }

    @Transactional
    public E save(E entity)throws Exception {
        try {
            entity = baseRepository.save(entity);
            return entity;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public E update(E entity) throws Exception {
        try {
            if (!baseRepository.existsById((ID) entity.getId())) {
                throw new Exception("Entidad no encontrada para actualizar");
            }
            return baseRepository.save(entity);
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Transactional
    public void delete(ID id) throws Exception{
        Optional<E> optionalEntity = baseRepository.findById(id);
        if (optionalEntity.isPresent()) {
            E entity = optionalEntity.get();
            entity.setDeleted(true);
            baseRepository.save(entity); // actualiza el estado en lugar de eliminarlo
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con id: " + id);
        }
    }
    @Transactional
    public Page<E> findAll(Pageable pageable) throws Exception {
        try {
            Page<E> entities = baseRepository.findAll(pageable);
            return entities;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
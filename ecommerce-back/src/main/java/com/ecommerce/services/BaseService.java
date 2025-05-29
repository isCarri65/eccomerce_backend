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

    public Optional<E> findById(ID id) {
        return baseRepository.findById(id);
    }

    @Transactional
    public E create(E entity) {
        return baseRepository.save(entity);
    }

    public E update(E entity) throws Exception {
        if (!baseRepository.existsById((ID) entity.getId())) {
            throw new Exception("Entidad no encontrada para actualizar");
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
}
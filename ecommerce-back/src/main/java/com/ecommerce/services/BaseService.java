package com.ecommerce.services;

import com.ecommerce.entities.Base;
import com.ecommerce.mappers.BaseAdminMapper;
import com.ecommerce.repositories.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public E findById(ID id) {
        return baseRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entidad no encontrada por su id: "+id));

    }
    public Optional<E> findByIdActive(ID id) {
        return baseRepository.findByIdAndDeleted(id, false);
    }

    @Transactional
    public E create(E entity) {
        return baseRepository.save(entity);
    }

    @Transactional
    public E update(ID id, E entity) {
        if (!baseRepository.existsById(id)) {
            throw new EntityNotFoundException("Entidad no encontrada para actualizar");
        }
        entity.setId((Long) id);
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

    @Transactional
    public void reallyDelete(ID id) {
        Optional<E> optionalEntity = baseRepository.findById(id);
        if (optionalEntity.isPresent()) {
            E entity = optionalEntity.get();
            baseRepository.delete(entity); // elimina totalmente
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con id: " + id);
        }
    }

    //Metodos personalizados para para recibir y devolver los dtos correspondientes

    @Transactional
    public <EntityDTO> List<EntityDTO> getAll(Function<E, EntityDTO> mapper) {
        System.out.println("service getAll");
        return baseRepository.findAll().stream().map(mapper).collect(Collectors.toList());
    }

    public <EntityDTO> Set<EntityDTO> getAllActives(Function<E, EntityDTO> mapper) {
        return baseRepository.getAllByDeleted(false).stream().map(mapper).collect(Collectors.toSet());
    }
    public <EntityDTO> Set<EntityDTO> getAllDeleted(Function<E, EntityDTO> mapper) {
        return baseRepository.getAllByDeleted(true).stream().map(mapper).collect(Collectors.toSet());
    }

    public <EntityDTO> EntityDTO findById(ID id, Function<E, EntityDTO> mapper) {
        E entity = baseRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entidad no encontrada por su id: "+id));
        return mapper.apply(entity);
    }
    public <EntityDTO> EntityDTO findByIdActive(ID id, Function<E, EntityDTO> mapper ) {
        E entity = baseRepository.findByIdAndDeleted(id, false).orElseThrow(()-> new EntityNotFoundException("Entidad no encontrada por su id: "+id));
        return mapper.apply(entity);
    }



    @Transactional
    public <EntityDTO, CreateDTO, UpdateDTO> EntityDTO create(CreateDTO dto, BaseAdminMapper<E, EntityDTO, CreateDTO, UpdateDTO> mapper) {
        E entity = mapper.CDTOtoEntity(dto);
        E entityCreated = baseRepository.save(entity);
        return mapper.toDTO(entityCreated);
    }
    @Transactional
    public <EntityDTO, CreateDTO, UpdateDTO> EntityDTO update(ID id, UpdateDTO dto, BaseAdminMapper<E, EntityDTO, CreateDTO, UpdateDTO> mapper) {
        E entity = baseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada para actualizar"));

        //actualizamos la entidad
        mapper.UDTOtoEntity(dto, entity);

        return mapper.toDTO(baseRepository.save(entity));
    }

}
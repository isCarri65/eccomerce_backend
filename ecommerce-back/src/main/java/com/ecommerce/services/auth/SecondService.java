package com.ecommerce.services.auth;

import com.ecommerce.entities.Base;
import com.ecommerce.mappers.BaseAdminMapper;
import com.ecommerce.repositories.BaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class SecondService<E extends Base, ID extends Serializable, EntityDTO, CreateDTO, UpdateDTO> {

    protected final BaseRepository<E, ID> baseRepository;
    protected final BaseAdminMapper<E, ID, EntityDTO, CreateDTO, UpdateDTO> baseMapper;

    public SecondService(BaseRepository<E, ID> baseRepository, BaseAdminMapper<E, ID, EntityDTO, CreateDTO, UpdateDTO> baseMapper) {
        this.baseRepository = baseRepository;
        this.baseMapper = baseMapper;

    }

    public List<EntityDTO> getAll() {
        return baseRepository.findAll().stream().map(baseMapper::toDTO).collect(Collectors.toList());
    }


    public Set<EntityDTO> getAllActives() {
        return baseRepository.getAllByDeleted(false).stream().map(baseMapper::toDTO).collect(Collectors.toSet());
    }
    public Set<EntityDTO> getAllDeleted() {
        return baseRepository.getAllByDeleted(true).stream().map(baseMapper::toDTO).collect(Collectors.toSet());
    }

    public EntityDTO findById(ID id) {
        E entity = baseRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entidad no encontrada por su id: "+id));
        return baseMapper.toDTO(entity);
    }
    public Optional<E> findByIdActive(ID id) {
        return baseRepository.findByIdAndDeleted(id, false);
    }

    @Transactional
    public EntityDTO create(CreateDTO entityDTO) {
        E entity = baseMapper.CDTOtoEntity(entityDTO);
        return baseMapper.toDTO(baseRepository.save(entity));
    }

    public EntityDTO update(ID id, UpdateDTO entityDTO) {
        if (!baseRepository.existsById(id)) {
            throw new EntityNotFoundException("Entidad no encontrada para actualizar");
        }
        E entity = baseMapper.UDTOtoEntity(entityDTO, id);
        return baseMapper.toDTO(baseRepository.save(entity));
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
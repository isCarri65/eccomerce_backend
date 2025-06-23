package com.ecommerce.mappers;

public interface BaseAdminMapper<E, ID, EntityDTO, CreateDTO, UpdateDTO> {
    E CDTOtoEntity (CreateDTO createDTO);
    E UDTOtoEntity (UpdateDTO updateDTO, ID id);
    EntityDTO toDTO (E entity);
}

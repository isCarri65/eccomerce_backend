package com.ecommerce.mappers;

public interface BaseAdminMapper<E, EntityDTO, CreateDTO, UpdateDTO> {
    E CDTOtoEntity (CreateDTO createDTO);
    void UDTOtoEntity (UpdateDTO updateDTO, E entity);
    EntityDTO toDTO (E entity);
}

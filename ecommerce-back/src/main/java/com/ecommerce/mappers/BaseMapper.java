package com.ecommerce.mappers;

public interface BaseMapper<E, EntityDTO> {
    EntityDTO toDTO (E entity);
}

package com.ecommerce.repositories;

import com.ecommerce.entities.Type;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends BaseRepository<Type, Long>{
    boolean existsByName(String name);
}

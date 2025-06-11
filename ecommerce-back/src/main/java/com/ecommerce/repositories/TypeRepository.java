package com.ecommerce.repositories;

import com.ecommerce.entities.Type;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends BaseRepository<Type, Long> {
    boolean existsByName(String name);

    List<Type> findAllByDeletedFalse();

    Optional<Type> findByIdAndDeletedFalse(Long id);
}

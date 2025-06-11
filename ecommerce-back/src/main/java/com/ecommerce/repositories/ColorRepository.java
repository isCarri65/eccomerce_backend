package com.ecommerce.repositories;

import com.ecommerce.entities.Color;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColorRepository extends BaseRepository<Color, Long> {
    boolean existsByName(String name);

    List<Color> findAllByDeletedFalse();

    Optional<Color> findByIdAndDeletedFalse(Long id);
}

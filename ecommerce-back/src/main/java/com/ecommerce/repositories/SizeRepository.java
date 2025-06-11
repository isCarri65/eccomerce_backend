package com.ecommerce.repositories;

import com.ecommerce.entities.Size;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SizeRepository extends BaseRepository<Size, Long> {
    boolean existsByName(String name);

    List<Size> findAllByDeletedFalse();

    Optional<Size> findByIdAndDeletedFalse(Long id);
}

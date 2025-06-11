package com.ecommerce.repositories;

import com.ecommerce.entities.Discount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends BaseRepository<Discount, Long> {
    List<Discount> findAllByDeletedFalse();
    Optional<Discount> findByIdAndDeletedFalse(Long id);
}

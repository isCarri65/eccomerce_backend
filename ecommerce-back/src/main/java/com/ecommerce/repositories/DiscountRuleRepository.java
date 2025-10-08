package com.ecommerce.repositories;

import com.ecommerce.entities.DiscountRule;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRuleRepository extends BaseRepository<DiscountRule, Long> {
    @Query("SELECT d FROM DiscountRule d " +
            "WHERE (" +
            "d.product.id = :productId OR " +
            "d.category.id IN :categoryIds" +
            ") " +
            "AND d.startDate <= CURRENT_DATE " +
            "AND d.endDate >= CURRENT_DATE")
    List<DiscountRule> findApplicableDiscountsForProduct(@Param("productId") Long productId,
                                                         @Param("categoryIds") List<Long> categoryIds);


    @Query("""
    SELECT d FROM DiscountRule d
    WHERE (
        d.product.id = :productId OR 
        d.category.id IN :categoryIds
    )
    AND d.startDate <= CURRENT_DATE 
    AND d.endDate >= CURRENT_DATE
    ORDER BY d.percentage DESC
""")
    List<DiscountRule> obtenerMejorDescuento(
            @Param("productId") Long productId,
            @Param("categoryIds") List<Long> categoryIds,
            Pageable pageable
    );
}

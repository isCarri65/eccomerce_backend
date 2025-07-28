package com.ecommerce.repositories;

import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductGenreENUM;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long>{
    Set<Product> getProductsByDeleted(boolean deleted);
    default Optional<Product> safeFindByIdWithVariants(Long id) {
        try {
            return Optional.ofNullable(findWithJoin(id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.productVariants WHERE p.id = :id")
    Product findWithJoin(@Param("id") Long id);

    @Query("""
SELECT p FROM Product p
JOIN p.productVariants pv
JOIN pv.size s
JOIN pv.color c
JOIN p.categories cat
JOIN cat.type t
WHERE (:genre IS NULL OR p.genre = :genre)
  AND (:minPrice IS NULL OR p.finalPrice >= :minPrice)
  AND (:maxPrice IS NULL OR p.finalPrice <= :maxPrice)
  AND (:sizeId IS NULL OR s.id = :sizeId)
  AND (:colorId IS NULL OR c.id = :colorId)
  AND (:typeId IS NULL OR t.id = :typeId)
  AND p.state = true
  AND pv.state = true
  AND pv.quantity > 0
  AND (:categoryIds IS NULL OR cat.id IN :categoryIds)
GROUP BY p.id
HAVING (:categoryIds IS NULL OR COUNT(DISTINCT cat.id) = :categoryCount)
""")
    Page<Product> findFilteredProducts(
            @Param("genre") ProductGenreENUM genre,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("sizeId") Long sizeId,
            @Param("colorId") Long colorId,
            @Param("categoryIds") List<Long> categoryIds,
            @Param("categoryCount") long categoryCount,
            @Param("typeId") Long typeId,
            Pageable pageable
    );
    @Query("""
    Select p From Product p
    WHERE LOWER(p.name) LIKE LOWER(CONCAT("%", :term, "%"))
    OR LOWER(p.description) LIKE LOWER(CONCAT("%", :term, "%") )
""")
    Page<Product> searchByNameOrDescription(@Param("term") String term, Pageable pageable);

    @Query("""
    SELECT p FROM Product p
    WHERE :category MEMBER OF p.categories
    """)
    Set<Product> findProductsByCategory(@Param("category") Category category);
}

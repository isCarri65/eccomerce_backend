package com.ecommerce.repositories;

import com.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long>{

    Set<Category> getAllByIdIn(Set<Long> ids);
    List<Category> findByTypeIdAndDeleted(Long typeId, boolean deleted);

    @Query("SELECT c FROM Category c WHERE :tag MEMBER OF c.tags")
    List<Category> findByTag(@Param("tag") String tag);
}

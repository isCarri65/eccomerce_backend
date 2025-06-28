package com.ecommerce.repositories;

import com.ecommerce.entities.Category;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long>{

    Set<Category> getAllByIdIn(Set<Long> ids);
}

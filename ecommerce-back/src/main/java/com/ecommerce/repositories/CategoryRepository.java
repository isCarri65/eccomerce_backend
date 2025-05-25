package com.ecommerce.repositories;

import com.ecommerce.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long>{
}

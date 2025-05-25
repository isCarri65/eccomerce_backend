package com.ecommerce.repositories;

import com.ecommerce.entities.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long>{
}

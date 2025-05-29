package com.ecommerce.repositories;

import com.ecommerce.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long>{
    Set<Product> getProductsByDeleted(boolean deleted);
}

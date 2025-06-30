package com.ecommerce.repositories;

import com.ecommerce.entities.ProductDiscount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDiscountRepository extends BaseRepository<ProductDiscount, Long>{
    List<ProductDiscount> findByProductId(Long productId);

}

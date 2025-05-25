package com.ecommerce.repositories;

import com.ecommerce.entities.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long>{
}

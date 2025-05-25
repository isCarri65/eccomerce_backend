package com.ecommerce.services;

import com.ecommerce.entities.Order;
import com.ecommerce.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseService<Order, Long>{
    public OrderService(OrderRepository orderRepository) {
        super(orderRepository);
    }
}

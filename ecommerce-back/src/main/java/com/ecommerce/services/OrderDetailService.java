package com.ecommerce.services;

import com.ecommerce.entities.OrderDetail;
import com.ecommerce.repositories.OrderDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService extends BaseService<OrderDetail, Long> {
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        super(orderDetailRepository);
    }
}

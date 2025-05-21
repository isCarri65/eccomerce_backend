package com.ecommerce.repositories;

import com.ecommerce.entities.PayingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayingOrderRepository extends JpaRepository<PayingOrder, Long> {

}

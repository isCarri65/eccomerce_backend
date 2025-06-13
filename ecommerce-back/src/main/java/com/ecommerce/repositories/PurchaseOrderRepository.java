package com.ecommerce.repositories;

import com.ecommerce.entities.PurchaseOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends BaseRepository<PurchaseOrder, Long>{
    List<PurchaseOrder> findAllByUser_id(Long userId);
}

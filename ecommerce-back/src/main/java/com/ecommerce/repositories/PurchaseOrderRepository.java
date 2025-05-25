package com.ecommerce.repositories;

import com.ecommerce.entities.PurchaseOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends BaseRepository<PurchaseOrder, Long>{
}

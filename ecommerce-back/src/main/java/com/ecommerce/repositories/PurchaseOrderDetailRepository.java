package com.ecommerce.repositories;

import com.ecommerce.entities.PurchaseOrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderDetailRepository extends BaseRepository<PurchaseOrderDetail, Long>{
    List<PurchaseOrderDetail> findByPurchaseOrderId(Long purchaseOrderId);
}

package com.ecommerce.services;

import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.repositories.PurchaseOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService extends BaseService<PurchaseOrder, Long>{
    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
        super(purchaseOrderRepository);
    }
}

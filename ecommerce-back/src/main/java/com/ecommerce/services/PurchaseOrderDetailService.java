package com.ecommerce.services;

import com.ecommerce.entities.PurchaseOrderDetail;
import com.ecommerce.repositories.PurchaseOrderDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderDetailService extends BaseService<PurchaseOrderDetail, Long> {
    public PurchaseOrderDetailService(PurchaseOrderDetailRepository purchaseOrderDetailRepository) {
        super(purchaseOrderDetailRepository);
    }
}

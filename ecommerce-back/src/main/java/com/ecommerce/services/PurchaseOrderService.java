package com.ecommerce.services;

import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.entities.PurchaseOrderStateENUM;
import com.ecommerce.repositories.PurchaseOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseOrderService extends BaseService<PurchaseOrder, Long>{
    private final PurchaseOrderRepository purchaseOrderRepository;
    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderRepository purchaseOrderRepository1) {
        super(purchaseOrderRepository);
        this.purchaseOrderRepository = purchaseOrderRepository1;
    }
    @Transactional
    public void marcarComoPagada(Long orderId) throws Exception {
        PurchaseOrder orden = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new Exception("No se encontró la orden con ID: " + orderId));

        orden.setState(PurchaseOrderStateENUM.PAID);
        purchaseOrderRepository.save(orden);
    }

}
package com.ecommerce.services;

import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.entities.PurchaseOrderDetail;
import com.ecommerce.repositories.PurchaseOrderDetailRepository;
import com.ecommerce.repositories.PurchaseOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderDetailService extends BaseService<PurchaseOrderDetail, Long> {
    private final PurchaseOrderDetailRepository purchaseOrderDetailRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderDetailService(PurchaseOrderDetailRepository purchaseOrderDetailRepository, PurchaseOrderRepository purchaseOrderRepository) {
        super(purchaseOrderDetailRepository);
        this.purchaseOrderDetailRepository = purchaseOrderDetailRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }
    public List<PurchaseOrderDetail> getAllByOrderId(Long id, Long userId) throws AccessDeniedException {
        PurchaseOrder order = purchaseOrderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PurchaseOrder not found"));
        if (!order.getUser().getId().equals(userId)){
            throw new AccessDeniedException("La orden no le pertenece al usuario");
        }

        return purchaseOrderDetailRepository.findByPurchaseOrderId(id);
    }
}

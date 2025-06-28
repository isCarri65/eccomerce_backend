package com.ecommerce.services;

import com.ecommerce.dto.PurchaseOrder.PurchaseOrderResponseDTO;
import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.entities.PurchaseOrderDetail;
import com.ecommerce.mappers.PurchaseOrderMapper;
import com.ecommerce.repositories.PurchaseOrderDetailRepository;
import com.ecommerce.repositories.PurchaseOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService extends BaseService<PurchaseOrder, Long>{
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    public PurchaseOrderService( PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderDetailRepository purchaseOrderDetailRepository) {
        super(purchaseOrderRepository);
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderDetailRepository = purchaseOrderDetailRepository;
    }

    public List<PurchaseOrderResponseDTO> getAllByUserId(Long userId){
        List<PurchaseOrder> orders =  purchaseOrderRepository.findAllByUser_id(userId);
        List<PurchaseOrderResponseDTO> dtos = new ArrayList<>();

        for (PurchaseOrder order : orders) {
            List<PurchaseOrderDetail> details = purchaseOrderDetailRepository.findByPurchaseOrderId(order.getId());
            dtos.add(PurchaseOrderMapper.toDTO(order, details));
        }
        return dtos;
    }

    public PurchaseOrderResponseDTO getPurchaseOrderById(Long id) {
        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PurchaseOrder not found"));

        // Cargar detalles desde su repositorio
        List<PurchaseOrderDetail> details = purchaseOrderDetailRepository.findByPurchaseOrderId(order.getId());

        return PurchaseOrderMapper.toDTO(order, details);
    }
}

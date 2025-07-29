package com.ecommerce.services;

import com.ecommerce.dto.PurchaseOrder.PurchaseOrderResponseDTO;
import com.ecommerce.dto.PurchaseOrder.PurchaseOrderResponseFullDTO;
import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.entities.PurchaseOrderDetail;
import com.ecommerce.entities.PurchaseOrderStateENUM;
import com.ecommerce.mappers.PurchaseOrderMapper;
import com.ecommerce.repositories.PurchaseOrderDetailRepository;
import com.ecommerce.repositories.PurchaseOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService extends BaseService<PurchaseOrder, Long>{
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderDetailRepository purchaseOrderDetailRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;

    public PurchaseOrderService( PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderMapper purchaseOrderMapper, PurchaseOrderDetailRepository purchaseOrderDetailRepository) {
        super(purchaseOrderRepository);
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderDetailRepository = purchaseOrderDetailRepository;
        this.purchaseOrderMapper = purchaseOrderMapper;
    }

    public List<PurchaseOrderResponseDTO> getAllByUserId(Long userId){
        List<PurchaseOrder> orders =  purchaseOrderRepository.findAllByUser_id(userId);
        List<PurchaseOrderResponseDTO> dtos = new ArrayList<>();

        for (PurchaseOrder order : orders) {
            List<PurchaseOrderDetail> details = purchaseOrderDetailRepository.findByPurchaseOrderId(order.getId());
            dtos.add(purchaseOrderMapper.toDTO(order, details));
        }
        return dtos;
    }

    @Transactional
    public PurchaseOrderResponseFullDTO getPurchaseOrderFullById(Long orderId){
        PurchaseOrder order =  purchaseOrderRepository.findById(orderId).orElseThrow(() -> new  EntityNotFoundException("Purchase Order not found"));
        List<PurchaseOrderDetail> details = purchaseOrderDetailRepository.findByPurchaseOrderId(orderId);
        return purchaseOrderMapper.toFullDTO(order, details);
    }



    public PurchaseOrderResponseDTO getPurchaseOrderById(Long id) {
        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PurchaseOrder not found"));

        // Cargar detalles desde su repositorio
        List<PurchaseOrderDetail> details = purchaseOrderDetailRepository.findByPurchaseOrderId(order.getId());

        return purchaseOrderMapper.toDTO(order, details);
    }
    @Transactional
    public void marcarComoPagada(Long orderId) throws Exception {
        PurchaseOrder orden = (PurchaseOrder)this.purchaseOrderRepository.findById(orderId).orElseThrow(() -> {
            return new Exception("No se encontró la orden con ID: " + orderId);
        });
        orden.setState(PurchaseOrderStateENUM.PAID);
        this.purchaseOrderRepository.save(orden);
    }
}

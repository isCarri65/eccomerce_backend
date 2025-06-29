package com.ecommerce.services;

import com.ecommerce.dto.CompraResponseDTO;
import com.ecommerce.dto.ProductCompraDTO;
import com.ecommerce.entities.*;
import com.ecommerce.repositories.DiscountRepository;
import com.ecommerce.repositories.ProductVariantRepository;
import com.ecommerce.repositories.PurchaseOrderDetailRepository;
import com.ecommerce.repositories.PurchaseOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderDetailService extends BaseService<PurchaseOrderDetail, Long> {
    private final ProductVariantRepository productVariantRepository;
    private final DiscountRepository discountRepository;
    private final PurchaseOrderDetailRepository purchaseOrderDetailRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderDetailService(PurchaseOrderDetailRepository purchaseOrderDetailRepository,
                                      ProductVariantRepository productVariantRepository,
                                      DiscountRepository discountRepository,
                                      PurchaseOrderRepository purchaseOrderRepository) {
        super(purchaseOrderDetailRepository);
        this.productVariantRepository = productVariantRepository;
        this.discountRepository = discountRepository;
        this.purchaseOrderDetailRepository = purchaseOrderDetailRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public List<PurchaseOrderDetail> getAllByOrderId(Long id, Long userId) throws AccessDeniedException {
        PurchaseOrder order = purchaseOrderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PurchaseOrder not found"));
        if (!order.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("La orden no le pertenece al usuario");
        }

        return purchaseOrderDetailRepository.findByPurchaseOrderId(id);
    }


    @Transactional
    public CompraResponseDTO generarOrdenCompra(List<ProductCompraDTO> productosDTO) throws Exception {
        List<PurchaseOrderDetail> detalles = new ArrayList<>();
        double precioTotal = 0.0;

        PurchaseOrder ordenCompra = PurchaseOrder.builder()
                .date(LocalDate.now())
                .finalPrice(0.0)
                .state(PurchaseOrderStateENUM.PENDING)
                .build();

        purchaseOrderRepository.save(ordenCompra);

        for (ProductCompraDTO dto : productosDTO) {
            ProductVariant pv = productVariantRepository.findById(dto.getVariantId())
                    .orElseThrow(() -> new Exception("No se encontró el ProductVariant con id: " + dto.getVariantId()));

            Product producto = pv.getProduct();
            double precioBase = producto.getSellPrice() * pv.getQuantity();

            Discount descuento = null;
            double porcentajeDescuento = 0.0;

            if (dto.getDiscountId() != null) {
                descuento = discountRepository.findById(dto.getDiscountId())
                        .orElseThrow(() -> new Exception("No se encontró el Discount con id: " + dto.getDiscountId()));
                porcentajeDescuento = descuento.getPercentage();
            }

            double precioFinal = precioBase * (1 - porcentajeDescuento / 100.0);

            PurchaseOrderDetail detalle = PurchaseOrderDetail.builder()
                    .purchaseOrder(ordenCompra)
                    .productVariant(pv)
                    .quantity(pv.getQuantity())
                    .unitPrice(precioFinal)
                    .totalPrice(precioFinal)
                    .discount(descuento)
                    .build();

            detalles.add(detalle);
            precioTotal += precioFinal;
        }


        ordenCompra.setFinalPrice(precioTotal);
        purchaseOrderRepository.save(ordenCompra);
        List<PurchaseOrderDetail> detallesGuardados = purchaseOrderDetailRepository.saveAll(detalles);

        return new CompraResponseDTO(ordenCompra.getId(), detallesGuardados);
    }


};
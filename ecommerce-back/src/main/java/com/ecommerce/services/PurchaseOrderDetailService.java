package com.ecommerce.services;

import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.entities.PurchaseOrderDetail;
import com.ecommerce.repositories.*;
import com.thoughtworks.qdox.model.expression.Add;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.repositories.PurchaseOrderDetailRepository;
import com.ecommerce.repositories.PurchaseOrderRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.AccessDeniedException;
import java.util.List;
import com.ecommerce.dto.CompraResponseDTO;
import com.ecommerce.dto.ProductCompraDTO;
import com.ecommerce.entities.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
@Service
public class PurchaseOrderDetailService extends BaseService<PurchaseOrderDetail, Long> {
    private final PurchaseOrderDetailRepository purchaseOrderDetailRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductVariantRepository productVariantRepository;
    private final DiscountRuleRepository discountRuleRepository;

    public PurchaseOrderDetailService(PurchaseOrderDetailRepository purchaseOrderDetailRepository,ProductVariantRepository productVariantRepository,
                                      DiscountRuleRepository discountRuleRepository, PurchaseOrderRepository purchaseOrderRepository) {
        super(purchaseOrderDetailRepository);
        this.productVariantRepository = productVariantRepository;
        this.discountRuleRepository = discountRuleRepository;
        this.purchaseOrderDetailRepository = purchaseOrderDetailRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }
    public List<PurchaseOrderDetail> getAllByOrderIdAndUserId(Long id, Long userId) throws AccessDeniedException {
        PurchaseOrder order = purchaseOrderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("PurchaseOrder not found"));
        if (!order.getUser().getId().equals(userId)){
            throw new AccessDeniedException("La orden no le pertenece al usuario");
        }

        return purchaseOrderDetailRepository.findByPurchaseOrderId(id);
    }


    @Transactional
    public CompraResponseDTO generarOrdenCompra(List<ProductCompraDTO> productosDTO, User user, Address address) throws Exception {
        List<PurchaseOrderDetail> detalles = new ArrayList<>();
        BigDecimal precioTotal = BigDecimal.ZERO;


        PurchaseOrder ordenCompra = PurchaseOrder.builder()
                .date(LocalDate.now())
                .finalPrice(BigDecimal.ZERO) // Cambiado a BigDecimal
                .state(PurchaseOrderStateENUM.PENDING)
                .user(user)
                .address(address)
                .build();

        purchaseOrderRepository.save(ordenCompra);

        for (ProductCompraDTO dto : productosDTO) {
            ProductVariant pv = productVariantRepository.findById(dto.getVariantId())
                    .orElseThrow(() -> new Exception("No se encontró el ProductVariant con id: " + dto.getVariantId()));

            Product producto = pv.getProduct();

            // Multiplicamos BigDecimal por cantidad (int)
            BigDecimal precioUnitario = producto.getSellPrice();
            BigDecimal cantidad = BigDecimal.valueOf(dto.getQuantityUser());
            BigDecimal precioBase = precioUnitario.multiply(cantidad);

            DiscountRule descuento = null;
            BigDecimal porcentajeDescuento = BigDecimal.ZERO;

            if (dto.getDiscountId() != null) {
                descuento = discountRuleRepository.findById(dto.getDiscountId())
                        .orElseThrow(() -> new Exception("No se encontró el Discount con id: " + dto.getDiscountId()));
                porcentajeDescuento = descuento.getPercentage();
            }

            // precioFinal = precioBase * (1 - porcentajeDescuento / 100)
            BigDecimal descuentoFactor = BigDecimal.ONE.subtract(porcentajeDescuento.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
            BigDecimal precioFinal = precioBase.multiply(descuentoFactor).setScale(2, RoundingMode.HALF_UP);

            PurchaseOrderDetail detalle = PurchaseOrderDetail.builder()
                    .purchaseOrder(ordenCompra)
                    .productVariant(pv)
                    .quantity(dto.getQuantityUser())
                    .unitPrice(precioUnitario)
                    .totalPrice(precioFinal)
                    .discount(descuento)
                    .build();

            detalles.add(detalle);
            precioTotal = precioTotal.add(precioFinal);

            if (pv.getQuantity() < dto.getQuantityUser()) {
                throw new Exception("Stock insuficiente para el ProductVariant con id: " + dto.getVariantId());
            }
            pv.setQuantity(pv.getQuantity() - dto.getQuantityUser());
            productVariantRepository.save(pv);
        }

        ordenCompra.setFinalPrice(precioTotal.setScale(2, RoundingMode.HALF_UP));
        purchaseOrderRepository.save(ordenCompra);
        List<PurchaseOrderDetail> detallesGuardados = purchaseOrderDetailRepository.saveAll(detalles);

        return new CompraResponseDTO(ordenCompra.getId(), detallesGuardados);
    }
}

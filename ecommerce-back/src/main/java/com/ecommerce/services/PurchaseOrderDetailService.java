package com.ecommerce.services;


import com.ecommerce.dto.ProductCompraDTO;
import com.ecommerce.entities.*;
import com.ecommerce.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderDetailService extends BaseService<PurchaseOrderDetail, Long>  {

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


    @Transactional
    public List<PurchaseOrderDetail> generarOrdenCompra(List<ProductCompraDTO> productosDTO) throws Exception {
        List<PurchaseOrderDetail> detalles = new ArrayList<>();
        double precioTotal = 0.0;

        PurchaseOrder ordenCompra = PurchaseOrder.builder()
                .date(LocalDate.now())
                .finalPrice(0.0) // se actualizará al final
                .build();

        purchaseOrderRepository.save(ordenCompra);

        for (ProductCompraDTO dto : productosDTO) {
            ProductVariant pv = productVariantRepository.findById(dto.getVariantId())
                    .orElseThrow(() -> new Exception("No se encontró el ProductVariant con id: " + dto.getVariantId()));

            Product producto = pv.getProduct();
            double precioBase = producto.getSellPrice();

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
                    .quantity(1)
                    .unitPrice(precioFinal)
                    .totalPrice(precioFinal)
                    .discount(descuento)
                    .build();

            detalles.add(detalle);
            precioTotal += precioFinal;
        }

        ordenCompra.setFinalPrice(precioTotal);
        purchaseOrderRepository.save(ordenCompra);
        return purchaseOrderDetailRepository.saveAll(detalles);
    }
}

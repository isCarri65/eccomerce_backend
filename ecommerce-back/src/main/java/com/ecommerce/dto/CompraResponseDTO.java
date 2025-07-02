package com.ecommerce.dto;

import com.ecommerce.entities.PurchaseOrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class CompraResponseDTO {
    private Long orderId;
    private List<PurchaseOrderDetail> detalles;
}

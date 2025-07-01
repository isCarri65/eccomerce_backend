package com.ecommerce.dto.PurchaseOrder;

import com.ecommerce.dto.Address.AddressDTO;
import com.ecommerce.dto.PurchaseOrderDetail.PurchaseOrderDetailDTO;
import com.ecommerce.dto.User.UserDTO;
import com.ecommerce.entities.PurchaseOrderStateENUM;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderResponseDTO {
    private Long id;
    private LocalDate date;
    private BigDecimal finalPrice;
    private String paymentMethod;
    private UserDTO user;
    private AddressDTO address;
    private PurchaseOrderStateENUM state;
    private List<PurchaseOrderDetailDTO> details;

    // Getters y Setters
}

package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "purchase_order_detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseOrderDetail extends Base{
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_order")
    private PurchaseOrder purchaseOrder;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_product_variant")
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private DiscountRule discount;

}

package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_order_detail")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseOrderDetail extends Base{
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "id_product_variant")
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private Discount discount;

}

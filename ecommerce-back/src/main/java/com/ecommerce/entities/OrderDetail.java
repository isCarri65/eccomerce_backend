package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product_variant")
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private Discount discount;

}

package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id @GeneratedValue
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

    @ManyToOne
    private Order idOrder;

    @ManyToOne
    private ProductVariant idProductVariant;

    @ManyToOne
    private Discount idDiscount;

}

package com.ecommerce.entities;

import jakarta.persistence.*;

@Entity
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

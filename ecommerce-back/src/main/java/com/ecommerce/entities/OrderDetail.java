package com.ecommerce.entities;

@Entity
public class Detail {
    @Id @GeneratedValue
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

    @ManyToOne
    private Order order;

    @ManyToOne
    private ProductVariant productVariant;

    @ManyToOne
    private Discount discount;

}

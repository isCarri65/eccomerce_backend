package com.ecommerce.entities;

@Entity
public class Detail {
    @Id @GeneratedValue
    private Long id;
    private Integer quantity;

    @ManyToOne
    private PayingOrder payingOrder;

    @ManyToOne
    private Product product;
}

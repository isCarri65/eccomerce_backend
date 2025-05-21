package com.ecommerce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Order {
    @Id @GeneratedValue
    private Long id;
    private LocalDate date;
    private Double finalPrice;
    private String paymentMethod;

    @ManyToOne
    private User idUser;

    @ManyToOne
    private Address idAddress;

    @Enumerated(EnumType.STRING)
    private OrderStateENUM state;

    @OneToMany(mappedBy = "Order")
    private List<OrderDetail> details;
}
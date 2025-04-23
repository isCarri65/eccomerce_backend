package com.ecommerce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class PayingOrder {
    @Id @GeneratedValue
    private Long id;
    private LocalDate date;
    private Double finalPrice;
    private String payMethod;
    private String state;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "payingOrder")
    private List<Detail> details;
}

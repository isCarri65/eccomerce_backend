package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order extends Base{
    private LocalDate date;
    private Double finalPrice;
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address; // <- esto se tiene que llamar tal cual se lo referencia en el mappedby

    @Enumerated(EnumType.STRING)
    private OrderStateENUM state;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> details;
}
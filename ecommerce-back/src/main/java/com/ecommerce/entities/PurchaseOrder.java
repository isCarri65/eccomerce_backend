package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchase_order")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseOrder extends Base{
    private LocalDate date;
    private Double finalPrice;
    private String paymentMethod;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStateENUM state;

}
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
@Table(name = "purchase_order")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PurchaseOrder extends Base{
    private LocalDate date;
    private Double finalPrice;
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStateENUM state;

    @OneToMany(mappedBy = "order")
    private List<PurchaseOrderDetail> details;
}
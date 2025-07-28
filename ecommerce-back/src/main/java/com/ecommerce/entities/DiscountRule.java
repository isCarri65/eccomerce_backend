package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "discountRule")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DiscountRule extends Base{
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal percentage;
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_Product")
    private Product product;

}

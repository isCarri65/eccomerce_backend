package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_discount")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDiscount extends Base{

    private Boolean state;
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private Discount discount;
}

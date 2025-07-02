package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product_variant")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = "product")

public class ProductVariant extends Base {

    private Integer quantity;
    private Boolean state;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

}

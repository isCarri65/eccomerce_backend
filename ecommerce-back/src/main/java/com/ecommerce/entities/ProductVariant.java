package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product_variant")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductVariant extends Base {

    private Integer quantity;
    private Boolean state;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;


    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @OneToMany(mappedBy = "productVariant")
    private List<OrderDetail> details;

}

package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "product_variant")
public class ProductVariant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

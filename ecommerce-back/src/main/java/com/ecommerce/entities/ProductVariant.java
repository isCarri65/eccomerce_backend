package com.ecommerce.entities;

@Entity
public class ProductVariant {
    @Id @GeneratedValue
    private Long id;
    private Integer quantity;
    private Boolean state;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Size size;

    @ManyToOne
    private Color color;

    @OneToMany(mappedBy = "ProductVariant")
    private List<Detail> details;

}


package com.ecommerce.entities;

import jakarta.persistence.*;

@Entity
public class Favorite {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User idUser;

    @ManyToOne
    private Product idProduct;

    private boolean state;
}


package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorite")
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

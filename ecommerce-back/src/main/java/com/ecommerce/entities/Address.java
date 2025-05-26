package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address extends Base{

    private String street;
    private Integer number;
    private String apartment;
    private String aptNumberAndFloor;
    private String province;
    private String locality;
    private String postal;

    @ManyToMany(mappedBy = "addresses")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "address")
    private List<PurchaseOrder> orders;
}

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
@Table(name = "type")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Type extends Base{
    private String name;

    @ManyToMany(mappedBy = "types")
    private Set<Category> categories = new HashSet<>();
}

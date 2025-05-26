package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "size")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Size extends Base{
    private String name;

    @Enumerated(EnumType.STRING)
    private SizeTypeENUM sizeType;

}

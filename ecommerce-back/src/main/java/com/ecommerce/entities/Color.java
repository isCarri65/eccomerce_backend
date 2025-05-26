package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "color")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Color extends Base{
    private String name;

}
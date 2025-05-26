package com.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "discount")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Discount extends Base{
    private LocalDate startDate;
    private LocalDate endDate;
    private Double percentage;
    private Boolean state;

}

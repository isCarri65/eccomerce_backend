package com.ecommerce.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiscountDTO {
    private Long id;
    private String name;
    private Double percentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active;
    private List<Long> productIds;
}

package com.ecommerce.dto;

import com.ecommerce.enums.SizeTypeENUM;
import lombok.Data;

@Data
public class SizeDTO {
    private Long id;
    private String name;
    private SizeTypeENUM type;
}
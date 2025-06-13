package com.ecommerce.dto.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressDTO {
    private String street;
    private Integer number;
    private String apartment;
    private String aptNumberAndFloor;
    private String province;
    private String locality;
    private String postal;
    private Long userId;
}

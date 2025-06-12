package com.ecommerce.dto.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UpdateAddressDTO {
    private String street;
    private Integer number;
    private String apartment;
    private String aptNumberAndFloor;
    private String province;
    private String locality;
    private String postal;
}

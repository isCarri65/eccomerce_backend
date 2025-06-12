package com.ecommerce.dto.Address;

import com.ecommerce.dto.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String street;
    private Integer number;
    private String apartment;
    private String aptNumberAndFloor;
    private String province;
    private String locality;
    private String postal;
    private Long userId;
}

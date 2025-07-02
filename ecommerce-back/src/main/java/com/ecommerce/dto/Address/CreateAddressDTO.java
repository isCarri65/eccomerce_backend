package com.ecommerce.dto.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressDTO {

    @NotBlank(message = "La calle es obligatoria")
    private String street;

    @NotNull(message = "El número es obligatorio")
    @Positive(message = "El número debe ser positivo")
    private Integer number;

    // Opcionales: pueden estar vacíos o nulos
    private String apartment;

    private String aptNumberAndFloor;

    @NotBlank(message = "La provincia es obligatoria")
    private String province;

    @NotBlank(message = "La localidad es obligatoria")
    private String locality;

    @NotBlank(message = "El código postal es obligatorio")
    private String postal;

    private Long userId;

}


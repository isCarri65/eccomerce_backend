package com.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompraRequestDTO {
    private List<ProductCompraDTO> productos;
    private Long idUser;
}

package com.ecommerce.dto;

import lombok.Data;

@Data
public class FavoriteDTO {
    private Long id;
    private Long idUser;
    private Long idProduct;
    private Boolean state;
}
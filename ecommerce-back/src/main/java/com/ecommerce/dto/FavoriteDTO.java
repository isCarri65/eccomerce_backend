package com.ecommerce.dto;

public record FavoriteDTO(Long id, Long idUser, Long idProduct, Boolean state) {
}
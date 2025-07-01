package com.ecommerce.entities;

import com.ecommerce.customException.InvalidRoleException;

public enum ProductGenreENUM {
    MALE,
    FEMALE,
    UNISEX,
    CHILDREN;
    public static ProductGenreENUM fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Genre cannot be null");
        }

        try {
            return ProductGenreENUM.valueOf(value.toUpperCase());
        } catch (InvalidRoleException e) {
            throw new InvalidRoleException("Valor de rol inválido: " + value);
        }
    }
}

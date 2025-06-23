package com.ecommerce.dto.Type;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TypeAdminDTO {
    private Long id;
    private String name;
    private boolean deleted;

}

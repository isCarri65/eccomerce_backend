package com.ecommerce.dto.Favorite;

import com.ecommerce.dto.Product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteAdminDTO {
    private Long id;
    private ProductDTO product;
    private Long userId;
}

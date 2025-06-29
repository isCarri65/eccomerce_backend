package com.ecommerce.dto.Favorite;

import com.ecommerce.dto.Product.ProductDTO;
import com.ecommerce.dto.User.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FavoriteDTO {
    private Long id;
    private ProductDTO product;
}

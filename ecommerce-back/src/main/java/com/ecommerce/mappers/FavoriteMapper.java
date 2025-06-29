package com.ecommerce.mappers;

import com.ecommerce.dto.Favorite.CreateFavoriteDTO;
import com.ecommerce.dto.Favorite.FavoriteDTO;
import com.ecommerce.dto.Product.ProductDTO;

import com.ecommerce.entities.Favorite;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class FavoriteMapper implements BaseMapper<Favorite, FavoriteDTO> {

    private final ProductMapper productMapper;
    @Override
    public FavoriteDTO toDTO(Favorite favorite) {
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setId(favorite.getId());

        ProductDTO productDTO = productMapper.toDTO(favorite.getProduct());
        favoriteDTO.setProduct(productDTO);
        return favoriteDTO;

    }

    public Favorite CDTOtoEntity(CreateFavoriteDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        Product product = new Product();
        product.setId(dto.getProductId());

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        return favorite;
    }
}

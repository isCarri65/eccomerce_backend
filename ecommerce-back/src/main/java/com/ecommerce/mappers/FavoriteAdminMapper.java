package com.ecommerce.mappers;

import com.ecommerce.dto.Favorite.CreateFavoriteDTO;
import com.ecommerce.dto.Favorite.FavoriteAdminDTO;
import com.ecommerce.dto.Favorite.UpdateFavoriteDTO;
import com.ecommerce.dto.Product.ProductDTO;
import com.ecommerce.dto.User.UserDTO;
import com.ecommerce.entities.Favorite;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import org.springframework.stereotype.Component;

@Component
public class FavoriteAdminMapper implements BaseAdminMapper<Favorite, Long, FavoriteAdminDTO, CreateFavoriteDTO, UpdateFavoriteDTO> {

    @Override
    public FavoriteAdminDTO toDTO(Favorite favorite) {
        FavoriteAdminDTO favoriteDTO = new FavoriteAdminDTO();
        favoriteDTO.setId(favorite.getId());
        favoriteDTO.setUserId(favorite.getUser().getId());
        ProductMapper productMapper = new ProductMapper();
        ProductDTO productDTO = productMapper.toDTO(favorite.getProduct());
        favoriteDTO.setProduct(productDTO);
        return favoriteDTO;

    }

    @Override
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
    @Override
    public Favorite UDTOtoEntity(UpdateFavoriteDTO dto, Long favoriteId) {
        Favorite favorite = new Favorite();
        favorite.setId(favoriteId);
        User user = new User();
        user.setId(dto.getUserId());
        Product product = new Product();
        product.setId(dto.getProductId());

        favorite.setUser(user);
        favorite.setProduct(product);
        return favorite;
    }
}

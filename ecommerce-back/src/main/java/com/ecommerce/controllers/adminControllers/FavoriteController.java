package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.Favorite.CreateFavoriteDTO;
import com.ecommerce.dto.Favorite.FavoriteAdminDTO;
import com.ecommerce.dto.Favorite.UpdateFavoriteDTO;
import com.ecommerce.entities.Favorite;
import com.ecommerce.mappers.FavoriteAdminMapper;
import com.ecommerce.services.FavoriteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/favorites")
public class FavoriteController extends BaseController<Favorite, Long, FavoriteAdminDTO, CreateFavoriteDTO, UpdateFavoriteDTO> {
    public FavoriteController(FavoriteService favoriteService, FavoriteAdminMapper favoriteAdminMapper) {
        super(favoriteService, favoriteAdminMapper);
    }
}

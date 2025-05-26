package com.ecommerce.controllers;

import com.ecommerce.entities.Favorite;
import com.ecommerce.services.FavoriteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController extends BaseController<Favorite, Long> {
    public FavoriteController(FavoriteService favoriteService) {
        super(favoriteService);
    }
}

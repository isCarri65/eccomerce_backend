package com.ecommerce.services;

import com.ecommerce.entities.Favorite;
import com.ecommerce.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService extends BaseService<Favorite, Long> {
    public FavoriteService(FavoriteRepository favoriteRepository) {
        super(favoriteRepository);
    }

}

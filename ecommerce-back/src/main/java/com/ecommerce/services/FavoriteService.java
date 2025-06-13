package com.ecommerce.services;

import com.ecommerce.entities.Favorite;
import com.ecommerce.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService extends BaseService<Favorite, Long> {
    private FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        super(favoriteRepository);
    }

    public List<Favorite> findAllByUserId(Long userId) {
        return favoriteRepository.findAllByUser_Id(userId);
    }

    public boolean existsByIdAndUserId(Long id, Long userId) {
        return favoriteRepository.existsByIdAndUser_Id(id, userId);
    }
}

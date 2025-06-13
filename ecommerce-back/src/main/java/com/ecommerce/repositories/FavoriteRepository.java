package com.ecommerce.repositories;

import com.ecommerce.entities.Favorite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends BaseRepository<Favorite, Long>{
    List<Favorite> findAllByUser_Id(Long id);
    boolean existsByIdAndUser_Id(Long id, Long userId);
}

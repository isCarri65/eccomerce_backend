package com.ecommerce.repositories;

import com.ecommerce.entities.Favorite;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends BaseRepository<Favorite, Long>{
}

package com.ecommerce.repositories;

import com.ecommerce.entities.Favorite;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends BaseRepository<Favorite, Long> {
    Optional<Favorite> findByIdUserAndIdProduct(Long idUser, Long idProduct);

    List<Favorite> findAllByIdUserAndStateTrue(Long idUser);
}

package com.ecommerce.services;

import com.ecommerce.dto.FavoriteDTO;
import com.ecommerce.entities.Favorite;
import com.ecommerce.repositories.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteService extends BaseService<Favorite, Long> {
    @Autowired
    private FavoriteRepository favoriteRepository;

    public FavoriteDTO createFavorite(FavoriteDTO dto) {
        Favorite favorite = Favorite.builder()
                .idUser(dto.idUser())
                .idProduct(dto.idProduct())
                .state(true)
                .build();

        return toDTO(favoriteRepository.save(favorite));
    }

    public FavoriteDTO editFavorite(FavoriteDTO dto) {
        Optional<Favorite> optional = favoriteRepository.findByIdUserAndIdProduct(dto.idUser(), dto.idProduct());
        if (optional.isEmpty()) throw new RuntimeException("Favorite no encontrado");

        Favorite favorite = optional.get();
        favorite.setState(dto.state()); // puede usarse si hay @Setter
        return toDTO(favoriteRepository.save(favorite));
    }

    public List<FavoriteDTO> getAllFavorites(Long idUser) {
        return favoriteRepository.findAllByIdUserAndStateTrue(idUser).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private FavoriteDTO toDTO(Favorite favorite) {
        return new FavoriteDTO(favorite.getId(), favorite.getIdUser(), favorite.getIdProduct(), favorite.getState());
    }

}

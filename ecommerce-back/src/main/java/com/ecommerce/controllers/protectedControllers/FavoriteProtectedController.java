package com.ecommerce.controllers.protectedControllers;

import com.ecommerce.dto.Favorite.CreateFavoriteDTO;
import com.ecommerce.dto.Favorite.FavoriteDTO;
import com.ecommerce.entities.Favorite;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.mappers.FavoriteMapper;
import com.ecommerce.services.FavoriteService;
import com.ecommerce.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profile/favorites")
public class FavoriteProtectedController {
    private final FavoriteService favoriteService;
    private final UserService userService;
    private final FavoriteMapper favoriteMapper;

    public FavoriteProtectedController(FavoriteService favoriteService, UserService userService, FavoriteMapper favoriteMapper) {
        this.favoriteService = favoriteService;
        this.userService = userService;
        this.favoriteMapper = favoriteMapper;
    }

    @GetMapping
    public ResponseEntity<List<FavoriteDTO>> getFavorites() {
        User user = userService.getCurrentUser();
        List<Favorite> favorites = favoriteService.findAllByUserId(user.getId());
        List<FavoriteDTO> favoritesDTO = favorites.stream().map(favoriteMapper::toDTO).toList();
        return ResponseEntity.ok(favoritesDTO);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getFavoritesProducts() {
        User user = userService.getCurrentUser();
        List<Favorite> favorites = favoriteService.findAllByUserId(user.getId());
        List<Product> products = favorites.stream().map(Favorite::getProduct).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @PostMapping("/addProduct/{id}")
    public ResponseEntity<FavoriteDTO> addFavorite(@PathVariable Long id) {
        User user = userService.getCurrentUser();
        CreateFavoriteDTO createDTO = CreateFavoriteDTO.builder().userId(user.getId()).productId(id).build();

        Favorite favorite = favoriteService.create(favoriteMapper.CDTOtoEntity(createDTO));
        return ResponseEntity.ok(favoriteMapper.toDTO(favorite));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long id) {
        User user = userService.getCurrentUser();
        if (!favoriteService.existsByIdAndUserId(id, user.getId())) {
            throw new EntityNotFoundException("El favorito con id " + id + " no existe, o no esta asignado a este usuario");
        }
        favoriteService.reallyDelete(id);
        return ResponseEntity.noContent().build();
    }
}

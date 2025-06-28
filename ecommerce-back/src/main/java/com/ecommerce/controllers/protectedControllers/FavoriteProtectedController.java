package com.ecommerce.controllers.protectedControllers;

import com.ecommerce.entities.Favorite;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.services.FavoriteService;
import com.ecommerce.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/protected/favorites")
public class FavoriteProtectedController {
    private final FavoriteService favoriteService;
    private final UserService userService;

    public FavoriteProtectedController(FavoriteService favoriteService, UserService userService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getFavoritesProducts() {
        User user = userService.getCurrentUser();
        List<Favorite> favorites = favoriteService.findAllByUserId(user.getId());
        List<Product> products = favorites.stream().map(Favorite::getProduct).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long id) {
        User user = userService.getCurrentUser();
        if(!favoriteService.existsByIdAndUserId(id, user.getId())){
            throw new EntityNotFoundException("El favorito con id " + id + " no existe, o no esta asignado a este usuario");
        }
        try {
                favoriteService.reallyDelete(id);
                return ResponseEntity.ok("Eliminado correctamente");
            } catch (EntityNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el recurso con ID: " + id);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el recurso.");
            }
    }
}

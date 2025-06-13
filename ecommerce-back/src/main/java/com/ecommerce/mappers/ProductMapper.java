package com.ecommerce.mappers;

import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.dto.Product.ProductCreateDTO;
import com.ecommerce.dto.Product.ProductDTO;
import com.ecommerce.dto.Product.ProductUpdateDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductGenreENUM;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductMapper {

    // Convert Product entity to ProductDTO (to send to frontend)
    public static ProductDTO toDTO(Product product) {
        if (product == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setBuyPrice(product.getBuyPrice());
        dto.setSellPrice(product.getSellPrice());
        dto.setDescription(product.getDescription());
        dto.setState(product.getState());
        dto.setGenre(product.getGenre() != null ? product.getGenre().name() : null);

        Set<CategoryDTO> categoryDTOS = product.getCategories().stream()
                .map(cat -> {
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setId(cat.getId());
                    categoryDTO.setName(cat.getName());
                    return categoryDTO;
                })
                .collect(Collectors.toSet());

        dto.setCategories(categoryDTOS);
        return dto;
    }

    // Convert CreateDTO to Product entity
    public static Product toEntity(ProductCreateDTO dto, Set<Category> categories) {
        if (dto == null) return null;

        return Product.builder()
                .name(dto.getName())
                .buyPrice(dto.getBuyPrice())
                .sellPrice(dto.getSellPrice())
                .description(dto.getDescription())
                .state(dto.getState())
                .genre(ProductGenreENUM.valueOf(dto.getGenre())) // convert String to Enum
                .categories(categories) // loaded categories from IDs
                .build();
    }

    // Convert UpdateDTO to Product entity (keeping the ID)
    public static Product toEntity(ProductUpdateDTO dto, Set<Category> categories) {
        if (dto == null) return null;
        Product product = Product.builder()
                .name(dto.getName())
                .buyPrice(dto.getBuyPrice())
                .sellPrice(dto.getSellPrice())
                .description(dto.getDescription())
                .state(dto.getState())
                .genre(ProductGenreENUM.valueOf(dto.getGenre()))
                .categories(categories)
                .build();
        product.setId(dto.getId());
        return product;
    }
}

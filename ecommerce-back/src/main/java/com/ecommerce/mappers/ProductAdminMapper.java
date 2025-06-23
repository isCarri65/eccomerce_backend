package com.ecommerce.mappers;

import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.dto.Product.CreateProductDTO;
import com.ecommerce.dto.Product.ProductAdminDTO;
import com.ecommerce.dto.Product.ProductAdminDTO;
import com.ecommerce.dto.Product.UpdateProductDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductGenreENUM;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductAdminMapper implements BaseAdminMapper<Product, Long, ProductAdminDTO, CreateProductDTO, UpdateProductDTO> {

    // Convert Product entity to ProductAdminDTO (to send to frontend)
    @Override
    public ProductAdminDTO toDTO(Product product) {
        if (product == null) return null;

        ProductAdminDTO dto = new ProductAdminDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setBuyPrice(product.getBuyPrice());
        dto.setSellPrice(product.getSellPrice());
        dto.setDescription(product.getDescription());
        dto.setState(product.getState());
        dto.setDeleted(product.isDeleted());
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
    @Override
    public Product CDTOtoEntity(CreateProductDTO dto) {
        if (dto == null) return null;
        Set<Category> categories = mapCategoryIdsToEntities(dto.getCategories());
        return Product.builder()
                .name(dto.getName())
                .buyPrice(dto.getBuyPrice())
                .sellPrice(dto.getSellPrice())
                .description(dto.getDescription())
                .state(dto.getState())
                .genre(ProductGenreENUM.fromString(dto.getGenre())) // convert String to Enum
                .categories(categories) // loaded categories from IDs
                .build();
    }

    // Convert UpdateDTO to Product entity (keeping the ID)
    @Override
    public Product UDTOtoEntity(UpdateProductDTO dto, Long id) {
        if (dto == null) return null;
        Set<Category> categories = mapCategoryIdsToEntities(dto.getCategoryIds());
        Product product = Product.builder()
                .name(dto.getName())
                .buyPrice(dto.getBuyPrice())
                .sellPrice(dto.getSellPrice())
                .description(dto.getDescription())
                .state(dto.getState())
                .genre(ProductGenreENUM.fromString(dto.getGenre()))
                .categories(categories)
                .build();
        product.setId(id);
        product.setDeleted(dto.isDeleted());
        return product;
    }
    private Set<Category> mapCategoryIdsToEntities(Set<Long> categoryIds) {
        return categoryIds.stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                })
                .collect(Collectors.toSet());
    }

}

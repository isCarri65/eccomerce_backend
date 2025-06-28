package com.ecommerce.mappers;

import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.dto.Product.CreateProductDTO;
import com.ecommerce.dto.Product.ProductAdminDTO;
import com.ecommerce.dto.Product.UpdateProductDTO;
import com.ecommerce.dto.productVariant.ProductVariantDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductGenreENUM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ProductAdminMapper implements BaseAdminMapper<Product, ProductAdminDTO, CreateProductDTO, UpdateProductDTO> {

    private  final ProductVariantMapper productVariantMapper;


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

        if (product.getProductVariants() != null) {

        List<ProductVariantDTO> productVariantDTOS = product.getProductVariants().stream().map(productVariantMapper::toDTO).toList();
        dto.setProductVariants(productVariantDTOS);
        }

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
    public void UDTOtoEntity(UpdateProductDTO dto, Product product) {
        Set<Category> categories = mapCategoryIdsToEntities(dto.getCategoryIds());
        product.setName(dto.getName());
        product.setBuyPrice(dto.getBuyPrice());
        product.setSellPrice(dto.getSellPrice());
        product.setDescription(dto.getDescription());
        product.setState(dto.getState());
        product.setGenre(ProductGenreENUM.fromString(dto.getGenre()));
        product.setCategories(categories);
        product.setDeleted(dto.isDeleted());
    }
    private Set<Category> mapCategoryIdsToEntities(List<Long> categoryIds) {
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : categoryIds) {
            Category category = new Category();
            category.setId(categoryId);
            categories.add(category);
        }
        return categories;
    }

}

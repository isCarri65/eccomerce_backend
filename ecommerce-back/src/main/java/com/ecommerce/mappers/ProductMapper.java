package com.ecommerce.mappers;

import com.ecommerce.dto.Category.CategoryDTO;
import com.ecommerce.dto.Product.CreateProductDTO;
import com.ecommerce.dto.Product.ProductDTO;

import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductGenreENUM;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements BaseMapper<Product, ProductDTO> {

    // Convert Product entity to ProductDTO (to send to frontend)
    @Override
    public ProductDTO toDTO(Product product) {
        if (product == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getSellPrice());
        dto.setDescription(product.getDescription());
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
}
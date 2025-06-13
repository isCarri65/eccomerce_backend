package com.ecommerce.services;

import com.ecommerce.dto.Product.ProductCreateDTO;
import com.ecommerce.dto.Product.ProductUpdateDTO;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Product;
import com.ecommerce.mappers.ProductMapper;
import com.ecommerce.repositories.CategoryRepository;
import com.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService extends BaseService<Product, Long>{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        super(productRepository);
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    // En ProductService:
    public Product createProduct(ProductCreateDTO dto) {
        Set<Category> categories = categoryRepository.getAllByIdIn(dto.getCategories())
                .stream().collect(Collectors.toSet());
        Product product = ProductMapper.toEntity(dto, categories);
        return productRepository.save(product);
    }

    public Product updateProduct(ProductUpdateDTO dto) {
        Set<Category> categories = categoryRepository.findAllById(dto.getCategories())
                .stream().collect(Collectors.toSet());
        Product product = ProductMapper.toEntity(dto, categories);
        return productRepository.save(product);
    }

}

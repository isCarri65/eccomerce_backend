package com.ecommerce.services;

import com.ecommerce.entities.Product;
import com.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductService extends BaseService<Product, Long>{
    public ProductService(ProductRepository productRepository) {
        super(productRepository);
    }

}

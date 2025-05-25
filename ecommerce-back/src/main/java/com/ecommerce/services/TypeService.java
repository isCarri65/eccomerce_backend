package com.ecommerce.services;

import com.ecommerce.entities.Type;
import com.ecommerce.repositories.TypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeService extends BaseService<Type, Long> {
    public TypeService(TypeRepository typeRepository) {
        super(typeRepository);
    }
}

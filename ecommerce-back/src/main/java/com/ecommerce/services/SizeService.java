package com.ecommerce.services;

import com.ecommerce.entities.Size;
import com.ecommerce.repositories.SizeRepository;
import org.springframework.stereotype.Service;

@Service
public class SizeService extends BaseService<Size,Long> {
    public SizeService(SizeRepository sizeRepository) {
        super(sizeRepository);
    }
}

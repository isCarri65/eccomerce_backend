package com.ecommerce.services;

import com.ecommerce.entities.Color;
import com.ecommerce.repositories.ColorRepository;
import org.springframework.stereotype.Service;

@Service
public class ColorService extends BaseService<Color, Long>{
    public ColorService(ColorRepository colorRepository) {
        super(colorRepository);
    }
}

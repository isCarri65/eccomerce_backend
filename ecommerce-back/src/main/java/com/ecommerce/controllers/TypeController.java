package com.ecommerce.controllers;

import com.ecommerce.entities.Base;
import com.ecommerce.entities.Type;
import com.ecommerce.services.TypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/types")
public class TypeController extends BaseController<Type, Long> {
    public TypeController(TypeService typeService) {
        super(typeService);
    }
}

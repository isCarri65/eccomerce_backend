package com.ecommerce.controllers;

import com.ecommerce.entities.Size;
import com.ecommerce.services.SizeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sizes")
public class SizeController extends BaseController<Size, Long> {
    public SizeController(SizeService sizeService) {
        super(sizeService);
    }
}

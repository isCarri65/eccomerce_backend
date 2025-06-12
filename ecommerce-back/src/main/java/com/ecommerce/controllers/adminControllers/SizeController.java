package com.ecommerce.controllers.adminControllers;

import com.ecommerce.entities.Size;
import com.ecommerce.services.SizeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/sizes")
public class SizeController extends BaseController<Size, Long> {
    public SizeController(SizeService sizeService) {
        super(sizeService);
    }
}

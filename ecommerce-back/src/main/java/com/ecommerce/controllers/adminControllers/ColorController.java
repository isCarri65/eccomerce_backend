package com.ecommerce.controllers.adminControllers;

import com.ecommerce.entities.Color;
import com.ecommerce.services.ColorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/colors")
public class ColorController extends BaseController<Color, Long> {
    public ColorController(ColorService colorService) {
        super(colorService);
    }
}

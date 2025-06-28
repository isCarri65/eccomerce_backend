package com.ecommerce.controllers.adminControllers;

import com.ecommerce.entities.User;
import com.ecommerce.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
public class UserController extends BaseController<User, Long> {

    public UserController(UserService userService) {
        super(userService);
    }
}

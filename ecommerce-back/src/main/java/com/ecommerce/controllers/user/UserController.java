package com.ecommerce.controllers.user;

import com.ecommerce.controllers.BaseController;
import com.ecommerce.entities.User;
import com.ecommerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<User, Long> {

    public UserController(UserService userService) {
        super(userService);
    }
}

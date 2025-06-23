package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.User.CreateUserAdminDTO;
import com.ecommerce.dto.User.UpdateUserAdminDTO;
import com.ecommerce.dto.User.UserAdminDTO;
import com.ecommerce.entities.User;
import com.ecommerce.mappers.UserAdminMapper;
import com.ecommerce.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
public class UserController extends BaseController<User, Long, UserAdminDTO, CreateUserAdminDTO, UpdateUserAdminDTO> {

    public UserController(UserService userService, UserAdminMapper mapper) {
        super(userService, mapper);
    }
}

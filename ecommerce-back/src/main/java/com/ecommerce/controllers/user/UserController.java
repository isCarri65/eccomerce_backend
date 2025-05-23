import com.ecommerce.entities.User;
import com.ecommerce.repositories.UserRepository;
import com.ecommerce.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(""/users"")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, HttpServletRequest request) {
        return ResponseEntity.ok(userService.createUser(user, request));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request) {
        if (!""ADMIN"".equals(request.getAttribute(""role""))) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping(""/{id}"")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newData, HttpServletRequest request) {
        if (!""ADMIN"".equals(request.getAttribute(""role""))) {
            return ResponseEntity.status(403).build();
        }

        return userService.updateUser(id, newData)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(""/{id}"")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, HttpServletRequest request) {
        if (!""ADMIN"".equals(request.getAttribute(""role""))) {
            return ResponseEntity.status(403).build();
        }

        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
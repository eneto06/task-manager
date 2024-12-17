package task_manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import task_manager.model.User;
import task_manager.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> listRegisteredUsers() {
        return ResponseEntity.ok().body(userService.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> searchUsers(@PathVariable Long id) {
        Optional<User> user = userService.findUsersById(id);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(user.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.findUsersById(id);

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOpt = userService.findUsersById(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        user.setId(id);

        return ResponseEntity.ok().body(userService.registerUser(user));

    }

}

package task_manager.controller;

import java.util.List;
import java.util.Objects;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import task_manager.dto.UserDto;
import task_manager.model.User;
import task_manager.service.UserService;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registerNewUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> listUsers() {
        return ResponseEntity.ok().body(userService.listUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> filterUserByName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.filterUserByName(name));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<UserDto>> filterUserByNameStartingWith(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.filterUserByNameStartingWith(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> searchUsers(@PathVariable Long id) {
        UserDto user = userService.findUsersById(id);

        if (Objects.isNull(user)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserDto> searchUserByCpf(@PathVariable String cpf) {
        UserDto user = userService.filterUserByCpf(cpf);

        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        UserDto user = userService.findUsersById(id);

        if (Objects.isNull(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        UserDto userOpt = userService.findUsersById(id);

        if (Objects.isNull(userOpt)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, user));
    }

}

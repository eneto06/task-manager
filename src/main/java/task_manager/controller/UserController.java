package task_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public User registerNewUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping
    public List<User> listRegisteredUsers() {
        return userService.listUsers();
    }


}

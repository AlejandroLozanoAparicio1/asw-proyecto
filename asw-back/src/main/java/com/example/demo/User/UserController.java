package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("user/users")
    public List<User> getUsers(){
        return userService.getUser();
    }

    @PostMapping("user/user")
    public void insertUser(@RequestBody User user ){
        userService.insertUser(user);
    }
}

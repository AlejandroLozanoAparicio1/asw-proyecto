package com.example.demo.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("userlogin")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }
    @GetMapping("user")
    public ResponseEntity<User> getUser(@RequestParam String username) {
        User user = userService.getUser(username);
        if (user != null) return ResponseEntity.ok().body(user);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> setUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user").toUriString());
        return ResponseEntity.created(uri).body(userService.modifyUser(user));
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> registerUser(@RequestParam String username) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/login").toUriString());
        return ResponseEntity.created(uri).body(userService.insertUser(username));
    }
}

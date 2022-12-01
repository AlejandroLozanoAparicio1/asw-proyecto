package com.example.demo.User;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("userlogin")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }
    @GetMapping("user")
    public User getUser(@RequestParam String username){
        return userService.getUser(username);
    }

    @PostMapping("userk")
    public User setUser(@RequestBody User user){
        return userService.modifyUser(user);
    }

    @PostMapping("user")
    public void registerUser(@RequestParam String username){
        userService.insertUser(username);
    }

}

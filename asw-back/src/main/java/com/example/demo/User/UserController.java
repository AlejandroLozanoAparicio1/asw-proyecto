package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    UserService userService = new UserService();
    @Autowired
    public HackNewsRepository hackNewsRepository;

    @GetMapping("user/get")
    public List<User> getUsers(){
        //return userService.getUser();
        return hackNewsRepository.findAll();
    }
}

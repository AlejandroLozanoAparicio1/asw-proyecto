package com.example.demo.Controllers;

import com.example.demo.Models.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    UserService userService = new UserService();

    @GetMapping("user/get")
    public List<User> getUsers(){
        return userService.getUser();
    }
}

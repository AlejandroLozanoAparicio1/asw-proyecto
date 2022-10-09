package com.example.demo;
import com.example.demo.User.HackNewsRepository;
import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Service
public class UserService {

    UserService(){

    }
    @Autowired
    private HackNewsRepository hackNewsRepository;
    public List<User> getUser(){
        return hackNewsRepository.findAll();
    }
}

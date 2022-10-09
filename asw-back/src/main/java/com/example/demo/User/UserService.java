package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class UserService {
    @Autowired
    private HackNewsRepository hackNewsRepository;
    public List<User> getUser(){
        return hackNewsRepository.findAll();
    }
}

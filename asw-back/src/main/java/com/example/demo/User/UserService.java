package com.example.demo.User;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    @Autowired
    public HackNewsRepository hackNewsRepository;

    public List<User> getUser(){
        return hackNewsRepository.findAll();
    }
}

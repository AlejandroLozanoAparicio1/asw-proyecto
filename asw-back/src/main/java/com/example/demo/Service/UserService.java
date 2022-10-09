package com.example.demo.Service;

import com.example.demo.Models.User;
import com.example.demo.Repositories.HackNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {
    @Autowired
    public HackNewsRepository hackNewsRepository;

    public List<User> getUser(){
        return hackNewsRepository.findAll();
    }
}

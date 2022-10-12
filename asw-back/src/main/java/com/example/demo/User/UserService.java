package com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void insertUser(User user){
        hackNewsRepository.save(user);
    }
}

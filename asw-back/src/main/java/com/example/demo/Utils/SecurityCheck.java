package com.example.demo.Utils;

import com.example.demo.User.User;
import com.example.demo.User.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityCheck {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityCheck(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean checkUserIsAuthenticated(String username, String apiKey){
        User user = userService.getUserByKey(apiKey);
        try{
            if(user.getUsername().equals(username))
                return true;
            return false;
        }
        catch (Exception e){
            return false;
        }
    }

}

package com.example.demo.Utils;

import com.example.demo.User.User;
import com.example.demo.User.UserService;
import org.springframework.stereotype.Service;

@Service
public class SecurityCheck {
    private final UserService userService;

    public SecurityCheck(UserService userService) {
        this.userService = userService;
    }

    public boolean checkUserIsAuthenticated(String username, String apiKey){
        User user = userService.checkByUsernameAndKey(username,apiKey);
        try{
            user.getUsername();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}

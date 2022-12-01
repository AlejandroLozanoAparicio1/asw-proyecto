package com.example.demo.Utils;

import com.example.demo.User.User;
import com.example.demo.User.UserRepository;

public class Security {
    private final UserRepository userRepository;

    public Security(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkApiKey(String apiKey){
        User user = userRepository.findUserByApiKey(apiKey);
        try{
            if(user.equals(null)){
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean checkApiKeyPost(String apiKey, String username){
        User user = userRepository.findUserByUsername(username);
        try{
            if(!user.equals(null)){
            }
            if(user.getApiKey().equals(apiKey)){
                return true;
            }
            return false;
        }
        catch (Exception e){
            return false;
        }
    }
}

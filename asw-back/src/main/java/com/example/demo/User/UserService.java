package com.example.demo.User;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class UserService  {
    private final UserRepository hackNewsRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private Random random = new Random();

    UserService(UserRepository hackNewsRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.hackNewsRepository = hackNewsRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<User> getUsers() {
        return hackNewsRepository.findAll();
    }

    public User getUser(String username) {
        if (hackNewsRepository.existsById(username)) {
            Optional<User> user = hackNewsRepository.findById(username);
            if (user.isPresent()) {
                return user.get();
            }
        }
        return null;
    }

    public User insertUser(String username) {
        User user = hackNewsRepository.findUserByUsername(username);
        if (user == null) {
            String apiKey = bCryptPasswordEncoder.encode(username.concat(random.ints(0, 1000000).toString()));
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date currentDate = Date.from(LocalDate.now().atStartOfDay(defaultZoneId).toInstant());
            User newUser = new User(
                    username, currentDate, 1, "", 20, 120, 0, false, false, apiKey, apiKey
            );
            return hackNewsRepository.save(newUser);
        }
        return null;
    }

    public User modifyUser(User user) {
        return hackNewsRepository.save(user);
    }


    public User checkByUsernameAndKey(String username, String apiKey){
        return hackNewsRepository.findUserByApiKeyAndUsername(apiKey, username);

    }
}

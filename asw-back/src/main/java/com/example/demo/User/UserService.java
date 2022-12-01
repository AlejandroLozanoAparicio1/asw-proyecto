package com.example.demo.User;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class UserService implements UserDetailsService {
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
        Optional<User> user = hackNewsRepository.findById(username);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public void insertUser(String username) {
        User user = hackNewsRepository.findUserByUsername(username);
        try {
            if (user.equals(null)) {
            }
        } catch (Exception e) {
            String apiKey = bCryptPasswordEncoder.encode(username.concat(random.ints(0, 1000000).toString()));
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date currentDate = Date.from(LocalDate.now().atStartOfDay(defaultZoneId).toInstant());
            User newUser = new User(
                    username, currentDate, 1, "", 20, 120, 0, false, false, apiKey, apiKey
            );
            hackNewsRepository.save(newUser);
        }
    }

    public User modifyUser(User user) {
        return hackNewsRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = hackNewsRepository.findUserByUsername(username);
        try{
            if(user.equals(null))
                throw new UsernameNotFoundException("User not found in the database.");
        }
        catch (Exception e){
        }
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()), authorities);
    }
}

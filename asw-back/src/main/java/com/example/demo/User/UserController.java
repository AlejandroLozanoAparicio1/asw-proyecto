package com.example.demo.User;


import com.example.demo.Utils.SecurityCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    public UserController(SecurityCheck securityCheck, UserService userService) {
        this.securityCheck = securityCheck;
        this.userService = userService;
    }

    private final SecurityCheck securityCheck;
    private final UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(
            @RequestHeader(value = "username") String userApi,
            @RequestHeader(value = "apiKey") String apikey
    ) {
        if (securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            return ResponseEntity.ok().body(userService.getUsers());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("user")
    public ResponseEntity<User> getUser(@RequestParam String username,
                                        @RequestHeader(value = "username") String userApi,
                                        @RequestHeader(value = "apiKey") String apikey) {
        if (securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            User user = userService.getUser(username);
            if (user != null) return ResponseEntity.ok().body(user);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping(value = "user", consumes =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> setUser(@RequestBody User user,
                                        @RequestHeader(value = "username") String userApi,
                                        @RequestHeader(value = "apiKey") String apikey) {
        if (securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user").toUriString());
            return ResponseEntity.created(uri).body(userService.modifyUser(user));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestParam String username) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user").toUriString());
        return ResponseEntity.created(uri).body(userService.insertUser(username));
        
    }

}

package com.example.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public boolean existsByUsername(String username);
    public User findUserByUsername(String username);
    public User findUserByApiKey(String apiKey);

    public User findUserByApiKeyAndUsername(String apiKey, String username);
}

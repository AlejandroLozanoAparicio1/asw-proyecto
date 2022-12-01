package com.example.demo.News;

import com.example.demo.User.User;

public class DTOLike {
    private User user;
    private Long id;

    public DTOLike(User user, Long id) {
        this.user = user;
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

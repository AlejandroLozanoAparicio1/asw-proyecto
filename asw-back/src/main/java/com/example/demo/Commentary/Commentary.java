package com.example.demo.Commentary;

import com.example.demo.User.User;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Commentary {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long itemId;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User username;
    @Column
    private String time;
    @Column
    private String title;
    @Column
    private String body;

    public Commentary() {}

    public Commentary(Long itemId, User user, String time, String title, String body) {
        this.itemId = itemId;
        this.username = user;
        this.time = time;
        this.title = title;
        this.body = body;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public User getUser() {
        return username;
    }

    public void setUser(User user) {
        this.username = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

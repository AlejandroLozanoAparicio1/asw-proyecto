package com.example.demo.Commentary;

import com.example.demo.Reply.Reply;
import com.example.demo.User.User;

import java.util.List;

public class CommentDTO {
    private Long id;
    private User user;
    private String time;
    private String body;
    private List<CommentDTO> replies;

    private List<User> likedBy;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<CommentDTO> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentDTO> replies) {
        this.replies = replies;
    }

    public CommentDTO(Long id, User user, String time, String body, List<CommentDTO> replies, List<User> likedBy) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.body = body;
        this.replies = replies;
        this.likedBy = likedBy;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }
}

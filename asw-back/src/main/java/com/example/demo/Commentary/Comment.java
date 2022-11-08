package com.example.demo.Commentary;

import com.example.demo.News.News;
import com.example.demo.User.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "username")
    private User user;
    @Column
    private String time;
    @Column
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "news", referencedColumnName = "itemId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private News news;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Comment() {}

    public Comment(Long id, User user, String time, String body, News news, List<Comment> comments) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.body = body;
        this.news = news;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long itemId) {
        this.id = itemId;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

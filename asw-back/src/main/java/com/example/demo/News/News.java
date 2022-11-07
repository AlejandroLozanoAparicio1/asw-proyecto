package com.example.demo.News;

import com.example.demo.Commentary.Comment;
import com.example.demo.User.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long itemId;
    @Column
    private String title;
    @Column
    private String page_;
    @Column
    private Integer points;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User username;
    @Column
    private String date_published;

    @Column
    private String link;

    /*
    @OneToMany()
    private List<Comment> comments = new ArrayList<Comment>();*/

    public News() {

    }

    public News(String title, String page, Integer points, User publisher, String datePublished, /*List<Comment> commentaries,*/ String link) {
        this.title = title;
        this.page_ = page;
        this.points = points;
        this.username = publisher;
        this.date_published = datePublished;
        //this.comments = commentaries;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPage_() {
        return page_;
    }

    public void setPage_(String page) {
        this.page_ = page;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User publisher) {
        this.username = publisher;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String datePublished) {
        this.date_published = datePublished;
    }

/*
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        System.out.println(comment.getTitle());
        this.comments.add(comment);
    }*/

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}

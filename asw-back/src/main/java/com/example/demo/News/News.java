package com.example.demo.News;

import com.example.demo.Commentary.Commentary;
import com.example.demo.User.User;

import java.util.List;

public class News {
    private String itemId;
    private String title;
    private String page;
    private Integer points;
    private User publisher;
    private String datePublised;
    private List<Commentary> commentaries;
    private String link;

    News(String title, String page, Integer points, User publisher, String datePublised, List<Commentary> commentaries,
         String link){
        this.title = title;
        this.page = page;
        this.points = points;
        this.publisher = publisher;
        this.datePublised = datePublised;
        this.commentaries = commentaries;
        this.link = link;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public String getDatePublised() {
        return datePublised;
    }

    public void setDatePublised(String datePublised) {
        this.datePublised = datePublised;
    }

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<Commentary> commentaries) {
        this.commentaries = commentaries;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

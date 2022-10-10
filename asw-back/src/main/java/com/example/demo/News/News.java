package com.example.demo.News;

import com.example.demo.Commentary.Commentary;
import com.example.demo.User.User;

import javax.persistence.*;
import java.util.List;

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
    private String page;
    @Column
    private Integer points;

    @OneToOne(mappedBy = "username")
    private User publisher;
    @Column
    private String datePublished;
    //@Column
    //private List<Commentary> comments;
    @Column
    private String link;

    public News() {

    }

    public News(String title, String page, Integer points, User publisher, String datePublished, /*List<Commentary> commentaries,*/
         String link){
        this.title = title;
        this.page = page;
        this.points = points;
        this.publisher = publisher;
        this.datePublished = datePublished;
        //this.comments = commentaries;
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

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    /*
    public List<Commentary> getComments() {
        return comments;
    }

    public void setComments(List<Commentary> comments) {
        this.comments = comments;
    }
    */
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

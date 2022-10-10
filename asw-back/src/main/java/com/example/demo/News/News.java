package com.example.demo.News;

import com.example.demo.User.User;

import javax.persistence.*;

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
         String link) {
        this.title = title;
        this.page_ = page;
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}

package com.example.demo.News;

import com.example.demo.Commentary.Comment;
import com.example.demo.Commentary.CommentRepository;
import com.example.demo.User.UserRepository;
import com.example.demo.User.User;
import com.example.demo.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    NewsService(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    public List<News> getNewsList() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));
    }

    public List<News> getNewest() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "datePublished"));
    }

    public Optional<News> getNews(Long id) {
        if (newsRepository.existsById(id)) return newsRepository.findById(id);
        return null;
    }

    public News createNews(News news) {
        if (news.getLink() != null && !news.getLink().equals("")) news.setType("url");
        else {
            news.setLink(null);
            news.setType("ask");
        }
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        news.setDatePublished(currentDateTime);
        news.setPoints(0);
        User user = userService.getUser(news.getUsername().getUsername());
        news.setUsername(user);
        return newsRepository.save(news);
    }


    public List<Comment> getComments(Long id) {
        News news = newsRepository.findById(id).get();
        if (news != null) {
            return news.getComments();
        }
        return null;
    }

    public Comment newComment(Long id, Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        comment.setTime(currentDateTime);
        if (newsRepository.existsById(id)) {
            News news = newsRepository.findById(id).get();
            Comment comment1 = commentRepository.save(comment);
            news.addComment(comment);
            newsRepository.save(news);
            return comment1;
        }
        return null;
    }

    public String like(Long id, User user) {
        if (newsRepository.existsById(id)) {
            News news = newsRepository.findById(id).get();
            int add = news.like(user);
            User us = userRepository.findUserByUsername(news.getUsername().getUsername());
            us.setKarma(us.getKarma() + add);
            userRepository.save(us);
            newsRepository.save(news);
            return "";
        }
        return null;
    }

    public List<News> getNewsAsk() {
        List<News> newsList = newsRepository.findAll();
        List<News> res = new ArrayList<>();

        for (int i = 0; i < newsList.size(); ++i) {
            if (newsList.get(i).getType().equals("ask"))
                res.add(newsList.get(i));
        }

        return res;
    }

    public List<News> getNewsShow() {
        List<News> newsList = newsRepository.findAll();
        List<News> res = new ArrayList<>();

        for (int i = 0; i < newsList.size(); ++i) {
            if (newsList.get(i).getType().equals("url"))
                res.add(newsList.get(i));
        }

        return res;
    }

    public List<News> getNewsByUsername(String username) {
        if (userRepository.existsByUsername(username)) return newsRepository.findAllByUsername(username);
        return null;
    }

    public List<News> getLikedNews(String username) {
        if (userRepository.existsByUsername(username)) return newsRepository.getLikedNews(username);
        return null;
    }
}

package com.example.demo.News;

import com.example.demo.Commentary.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("news")
    public List<News> getNewsList() {
        return newsService.getNewsList();
    }

    @GetMapping("newest")
    public List<News> getNewest() {
        return newsService.getNewest();
    }

    @GetMapping("news/get")
    public Optional<News> getNews(Long id) {
        return newsService.getNews(id);
    }

    @PostMapping("submit")
    public void getNews(@RequestBody News news) {
        newsService.createNews(news);
    }

    /*
    @GetMapping("news/get/comments")
    public List<Comment> getComments(Long id) {
        return newsService.getComments(id);
    }

    @PutMapping ("news/comment/{id}")
    public void addComment(Long id, @PathVariable("id") Long commentId) {
        newsService.newComment(id, commentId);
    }*/
}

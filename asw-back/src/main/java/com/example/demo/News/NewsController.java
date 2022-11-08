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

    @GetMapping("news/{id}")
    public Optional<News> getNews(@PathVariable Long id) {
        return newsService.getNews(id);
    }

    @PostMapping("submit")
    public void getNews(@RequestBody News news) {
        newsService.createNews(news);
    }


    @GetMapping("news/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id) {
        return newsService.getComments(id);
    }

    @PutMapping("news/{id}/newcomment")
    public void addComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        newsService.newComment(id, comment);
    }
}

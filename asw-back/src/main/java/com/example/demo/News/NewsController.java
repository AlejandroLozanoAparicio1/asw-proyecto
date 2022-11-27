package com.example.demo.News;

import com.example.demo.Commentary.Comment;
import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping("news")
    public ResponseEntity<List<News>> getNewsList() {
        return ResponseEntity.ok().body(newsService.getNewsList());
    }

    @GetMapping("ask")
    public ResponseEntity<List<News>> getNewsAsk() {
        return ResponseEntity.ok().body(newsService.getNewsAsk());
    }

    @GetMapping("show")
    public ResponseEntity<List<News>> getNewsShow() {
        return ResponseEntity.ok().body(newsService.getNewsShow());
    }

    @GetMapping("newest")
    public ResponseEntity<List<News>> getNewest() {
        return ResponseEntity.ok().body(newsService.getNewest());
    }

    @GetMapping("news/{id}")
    @ResponseBody
    public ResponseEntity<Optional<News>> getNews(@PathVariable Long id) throws Exception {
        Optional<News> news = newsService.getNews(id);
        if (news != null) {
            return ResponseEntity.ok().body(news);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("submit")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<News> createNews(@RequestBody News news) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/submit").toUriString());
        return ResponseEntity.created(uri).body(newsService.createNews(news));

    }

    @GetMapping("news/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long id) {
        return ResponseEntity.ok().body(newsService.getComments(id));
    }

    @PutMapping("news/{id}/newcomment")
    public void addComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        newsService.newComment(id, comment);
    }

    @PutMapping("news/{id}/like")
    public void like(@PathVariable("id") Long id, @RequestBody User user) {
        newsService.like(id, user);
    }

    @GetMapping("news/user")
    public ResponseEntity<List<News>> getNewsByUsername(@RequestParam String username) {
        return ResponseEntity.ok().body(newsService.getNewsByUsername(username));
    }
    @GetMapping("news/liked")
    public ResponseEntity<List<News>> getLikedNews(@RequestParam String username){
        return ResponseEntity.ok().body(newsService.getLikedNews(username));
    }

}

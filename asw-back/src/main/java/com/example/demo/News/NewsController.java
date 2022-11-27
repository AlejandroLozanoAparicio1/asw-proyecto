package com.example.demo.News;

import com.example.demo.Commentary.Comment;
import com.example.demo.User.User;
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

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    private final NewsService newsService;

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
        List<Comment> list = newsService.getComments(id);
        if (list != null) return ResponseEntity.ok().body(list);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("news/{id}/newcomment")
    public ResponseEntity<Comment> addComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        Comment comment1 = newsService.newComment(id, comment);
        if (comment1 != null) return ResponseEntity.ok().body(comment1);
        return null;
    }

    @PutMapping("news/{id}/like")
    public ResponseEntity<String> like(@PathVariable("id") Long id, @RequestBody User user) throws Exception {
        if (newsService.like(id, user) != null) {
            return ResponseEntity.ok().body("");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    }

    @GetMapping("news/user")
    public ResponseEntity<List<News>> getNewsByUsername(@RequestParam String username) {
        return ResponseEntity.ok().body(newsService.getNewsByUsername(username));
    }

    @GetMapping("news/liked")
    public ResponseEntity<List<News>> getLikedNews(@RequestParam String username) {
        return ResponseEntity.ok().body(newsService.getLikedNews(username));
    }

}

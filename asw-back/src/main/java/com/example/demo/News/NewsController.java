package com.example.demo.News;

import com.example.demo.Commentary.Comment;
import com.example.demo.User.User;
import com.example.demo.Utils.SecurityCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {
    private final SecurityCheck securityCheck;

    public NewsController(SecurityCheck securityCheck, NewsService newsService) {
        this.securityCheck = securityCheck;
        this.newsService = newsService;
    }

    private final NewsService newsService;

    @GetMapping("news")
    public ResponseEntity<List<News>> getNewsList(
            @RequestHeader(value = "username") String userApi,
            @RequestHeader(value = "apiKey") String apikey
    ) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            return ResponseEntity.ok().body(newsService.getNewsList());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    @GetMapping("ask")
    public ResponseEntity<List<News>> getNewsAsk(
            @RequestHeader(value = "username") String userApi,
            @RequestHeader(value = "apiKey") String apikey
    ) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            return ResponseEntity.ok().body(newsService.getNewsAsk());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("show")
    public ResponseEntity<List<News>> getNewsShow(
            @RequestHeader(value = "username") String userApi,
            @RequestHeader(value = "apiKey") String apikey
    ) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            return ResponseEntity.ok().body(newsService.getNewsShow());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("newest")
    public ResponseEntity<List<News>> getNewest(
            @RequestHeader(value = "username") String userApi,
            @RequestHeader(value = "apiKey") String apikey
    ) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            return ResponseEntity.ok().body(newsService.getNewest());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @CrossOrigin
    @GetMapping("news/{id}")
    public ResponseEntity<Optional<News>> getNews(@PathVariable Long id,
                                                  @RequestHeader(value = "username") String userApi,
                                                  @RequestHeader(value = "apiKey") String apikey) throws Exception {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            Optional<News> news = newsService.getNews(id);
            if (news != null) {
                return ResponseEntity.ok().body(news);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PostMapping("submit")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<News> createNews(@RequestBody News news,
                                           @RequestHeader(value = "username") String userApi,
                                           @RequestHeader(value = "apiKey") String apikey) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/submit").toUriString());
            return ResponseEntity.created(uri).body(newsService.createNews(news));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("news/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long id,
                                                     @RequestHeader(value = "username") String userApi,
                                                     @RequestHeader(value = "apiKey") String apikey) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            List<Comment> list = newsService.getComments(id);
            if (list != null) return ResponseEntity.ok().body(list);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PutMapping("news/{id}/newcomment")
    public ResponseEntity<Comment> addComment(@PathVariable("id") Long id, @RequestBody Comment comment,
                                              @RequestHeader(value = "username") String userApi,
                                              @RequestHeader(value = "apiKey") String apikey) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            Comment comment1 = newsService.newComment(id, comment);
            if (comment1 != null) return ResponseEntity.ok().body(comment1);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PutMapping("news/{id}/like")
    public ResponseEntity<String> like(@PathVariable("id") Long id, @RequestBody User user,
                                       @RequestHeader(value = "username") String userApi,
                                       @RequestHeader(value = "apiKey") String apikey) throws Exception {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            if (newsService.like(id, user) != null) {
                return ResponseEntity.ok().body("");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("news/user")
    public ResponseEntity<List<News>> getNewsByUsername(@RequestParam String username,
                                                        @RequestHeader(value = "username") String userApi,
                                                        @RequestHeader(value = "apiKey") String apikey) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            return ResponseEntity.ok().body(newsService.getNewsByUsername(username));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("news/liked")
    public ResponseEntity<List<News>> getLikedNews(@RequestParam String username,
                                                   @RequestHeader(value = "username") String userApi,
                                                   @RequestHeader(value = "apiKey") String apikey) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            return ResponseEntity.ok().body(newsService.getLikedNews(username));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}

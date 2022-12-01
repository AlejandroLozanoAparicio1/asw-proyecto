package com.example.demo.Commentary;

import com.example.demo.News.DTOLike;
import com.example.demo.User.User;
import com.example.demo.Utils.SecurityCheck;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
public class CommentController {

    private final CommentService commentService;
    private final SecurityCheck securityCheck;

    public CommentController(CommentService commentService, SecurityCheck securityCheck) {
        this.commentService = commentService;
        this.securityCheck = securityCheck;
    }

    @GetMapping("commentlist/get")
    public ResponseEntity<List<Comment>> getCommentList(
            @RequestHeader(value = "username") String userApi,
            @RequestHeader(value = "apiKey") String apikey
    ) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            return ResponseEntity.ok().body(commentService.getCommentList());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("comment/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable("id") Long id,
                                                 @RequestHeader(value = "username") String userApi,
                                                 @RequestHeader(value = "apiKey") String apikey) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            CommentDTO com = commentService.getComment(id);
            if (com != null) return ResponseEntity.ok().body(com);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @CrossOrigin
    @GetMapping("comment/user/{id}")
    public ResponseEntity<List<Comment>> getUserComments(@PathVariable("id") String id,
                                                         @RequestHeader(value = "username") String userApi,
                                                         @RequestHeader(value = "apiKey") String apikey) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            List<Comment> list = commentService.getUserComments(id);
            if (list != null) return ResponseEntity.ok().body(list);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

    }

    @PutMapping("news/{id}/reply")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Comment> addReply(@PathVariable("id") Long id, @RequestBody Comment comment,
                                            @RequestHeader(value = "username") String userApi,
                                            @RequestHeader(value = "apiKey") String apikey) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            Comment com = commentService.addReply(id, comment);
            if (com != null) {
                URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("news/{id}/reply").toUriString());
                return ResponseEntity.created(uri).body(com);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    @PutMapping("comment")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment,
                                              @RequestHeader(value = "username") String userApi,
                                              @RequestHeader(value = "apiKey") String apikey
                                              ) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/submit").toUriString());
            return ResponseEntity.created(uri).body(commentService.newComment(comment));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @PutMapping("comment/{id}/like")
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> like(@RequestBody DTOLike dtoLike,
                                       @RequestHeader(value = "username") String userApi,
                                       @RequestHeader(value = "apiKey") String apikey) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            if (commentService.like(dtoLike.getId(), dtoLike.getUser())) {
                ResponseEntity.ok().body("");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("comments/liked")
    public ResponseEntity<List<Comment>> liked(@RequestParam String username,
                                               @RequestHeader(value = "username") String userApi,
                                               @RequestHeader(value = "apiKey") String apikey
                                               ) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            List<Comment> list = commentService.liked(username);
            if (list != null) return ResponseEntity.ok().body(list);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("comments/news/{id}")
    public ResponseEntity<List<Long>> getNewsComments(@PathVariable long id,
                                                      @RequestHeader(value = "username") String userApi,
                                                      @RequestHeader(value = "apiKey") String apikey
    ) {
        if(securityCheck.checkUserIsAuthenticated(userApi, apikey)) {
            List<Long> list = commentService.getNewsComments(id);
            if (list != null) return ResponseEntity.ok().body(list);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}

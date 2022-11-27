package com.example.demo.Commentary;

import com.example.demo.Reply.Reply;
import com.example.demo.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("commentlist/get")
    public ResponseEntity<List<Comment>> getCommentList() {
        return ResponseEntity.ok().body(commentService.getCommentList());
    }

    @GetMapping("comment/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable("id") Long id) {
        CommentDTO com = commentService.getComment(id);
        if (com != null) return ResponseEntity.ok().body(com);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("comment/user/{id}")
    public ResponseEntity<List<Comment>> getUserComments(@PathVariable("id") String id) {
        List<Comment> list = commentService.getUserComments(id);
        if (list != null) return ResponseEntity.ok().body(list);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PutMapping("news/{id}/reply")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Comment> addReply(@PathVariable("id") Long id, @RequestBody Comment comment) {
        Comment com = commentService.addReply(id, comment);
        if (com != null) {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("news/{id}/reply").toUriString());
            return ResponseEntity.created(uri).body(com);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    @PutMapping("comment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/submit").toUriString());
        return ResponseEntity.created(uri).body(commentService.newComment(comment));
    }

    @PutMapping("comment/{id}/like")
    public ResponseEntity<String> like(@PathVariable("id") Long id, @RequestBody User user) {
        if (commentService.like(id, user)) {
            ResponseEntity.ok().body("");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("comments/liked")
    public ResponseEntity<List<Comment>> liked(@RequestParam String username) {
        return ResponseEntity.ok().body(commentService.liked(username));
    }

    @GetMapping("comments/news/{id}")
    public ResponseEntity<List<Long>> getNewsComments(@PathVariable long id) {
        List<Long> list = commentService.getNewsComments(id);
        if (list != null) return ResponseEntity.ok().body(list);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}

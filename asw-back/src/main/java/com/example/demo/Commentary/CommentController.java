package com.example.demo.Commentary;

import com.example.demo.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok().body(commentService.getComment(id));
    }

    @GetMapping("comment/user/{id}")
    public ResponseEntity<List<Comment>> getUserComments(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(commentService.getUserComments(id));
    }

    @PutMapping("news/{id}/reply")
    public void addReply(@PathVariable("id") Long id, @RequestBody Comment comment) {
        commentService.addReply(id, comment);
    }
    @PutMapping("comment")
    public void addComment( @RequestBody Comment comment) {
        commentService.newComment(comment);
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
        return ResponseEntity.ok().body(commentService.getNewsComments(id));
    }
}

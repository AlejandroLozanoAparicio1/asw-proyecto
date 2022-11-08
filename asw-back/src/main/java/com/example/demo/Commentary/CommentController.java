package com.example.demo.Commentary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;


    @GetMapping("commentlist/get")
    public List<Comment> getCommentList() {
        return commentService.getCommentList();
    }

    @GetMapping("comment/{id}")
    public Comment getComment(@PathVariable("id") Long id) {
        return commentService.getComment(id);
    }

    @PostMapping("comment/post")
    public void getComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
    }
}

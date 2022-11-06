package com.example.demo.Commentary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;


    @GetMapping("commentlist/get")
    public List<Commentary> getCommentList() {
        return commentService.getCommentList();
    }

    @GetMapping("comment/get")
    public Optional<Commentary> getComment(Long id) {
        return commentService.getComment(id);
    }

    @PostMapping("comment/post")
    public void getComment(@RequestBody Commentary comment) {
        commentService.createComment(comment);
    }
}

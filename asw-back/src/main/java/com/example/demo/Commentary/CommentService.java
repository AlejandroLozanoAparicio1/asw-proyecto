package com.example.demo.Commentary;

import com.example.demo.News.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    CommentService() {}

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentList() {
        List<Comment> a = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        return a;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).get();
    }


    public void newComment(Long id, Comment reply) {
        commentRepository.save(reply);
        Comment comment = commentRepository.findById(id).get();
        comment.addComments(reply);
        commentRepository.save(comment);
    }
}

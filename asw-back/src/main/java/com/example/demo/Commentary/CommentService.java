package com.example.demo.Commentary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    CommentService() {}

    @Autowired
    private CommentRepository commRepository;

    public List<Comment> getCommentList() {
        return commRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
    }

    public Optional<Comment> getComment(Long id) {
        return commRepository.findById(id);
    }

    public void createComment(Comment comment) {
        commRepository.save(comment);
    }
}

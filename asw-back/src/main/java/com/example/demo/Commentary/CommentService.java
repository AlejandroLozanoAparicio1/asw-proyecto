package com.example.demo.Commentary;

import com.example.demo.News.News;
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

    public List<Commentary> getCommentList() {
        return commRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
    }

    public Optional<Commentary> getComment(Long id) {
        return commRepository.findById(id);
    }

    public void createComment(Commentary comment) {
        commRepository.save(comment);
    }
}

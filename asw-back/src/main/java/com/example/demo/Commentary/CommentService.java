package com.example.demo.Commentary;

import com.example.demo.News.News;
import com.example.demo.User.HackNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    CommentService() {}

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private HackNewsRepository userRepository;

    public List<Comment> getCommentList() {
        List<Comment> a = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        return a;
    }

    public Comment getComment(Long id) {
        return commentRepository.findById(id).get();
    }

    public List<Comment> getUserComments(String id) {
        userRepository.findById(id);
        List<Comment> comments = commentRepository.findAll();
        List<Comment> res = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getUser().getUsername() == id)
                res.add(comment);
        }
        return res;
    }


    public void newComment(Long id, Comment reply) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        reply.setTime(currentDateTime);

        Comment comment = commentRepository.findById(id).get();
        reply.setParent(comment);

        commentRepository.save(reply);
        comment.addComments(reply);
        commentRepository.save(comment);
    }
}

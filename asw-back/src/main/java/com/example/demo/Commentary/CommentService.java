package com.example.demo.Commentary;

import com.example.demo.Reply.Reply;
import com.example.demo.User.UserRepository;
import com.example.demo.User.User;
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
    private UserRepository userRepository;

    public List<Comment> getCommentList() {
        List<Comment> a = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        return a;
    }

    public CommentDTO getComment(Long id) {
        if (commentRepository.existsById(id)) {
            Comment comment = commentRepository.findById(id).get();
            CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getUser(), comment.getTime(), comment.getBody(), new ArrayList<CommentDTO>(), new ArrayList<User>());
            List<Reply> replies = comment.getReplies();
            getReplies(comment.getReplies(), commentDTO.getReplies());
            return commentDTO;
        }
        return null;
    }

    private void getReplies(List<Reply> replies, List<CommentDTO> comments){
        if(replies.isEmpty())
            return;
        Comment comment;
        for(Reply reply : replies) {
            comment = commentRepository.findById(reply.getComenntaryId()).get();
            CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getUser(), comment.getTime(), comment.getBody(), new ArrayList<CommentDTO>(), new ArrayList<User>());
            comments.add(commentDTO);
            getReplies(comment.getReplies(), commentDTO.getReplies());
        }

    }

    public List<Comment> getUserComments(String id) {
        if (userRepository.existsById(id)) {
            userRepository.findById(id);
            List<Comment> comments = commentRepository.findAll();
            List<Comment> res = new ArrayList<>();
            for (Comment comment : comments) {
                if (comment.getUser().getUsername() == id)
                    res.add(comment);
            }
            return res;
        }
        return null;
    }


    public Comment newComment(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        comment.setTime(currentDateTime);
        return commentRepository.save(comment);
    }

    public Comment addReply(Long id, Comment reply) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        reply.setTime(currentDateTime);

        if (commentRepository.existsById(id)) {
            Comment comment = commentRepository.findById(id).get();
            Comment reply2 = commentRepository.save(reply);
            comment.addComments(new Reply(reply.getId()));
            commentRepository.save(comment);
            return reply2;
        }
        return null;
    }


    public Boolean like(Long id, User user) {

        if (commentRepository.existsById(id)) {
            Comment comment = commentRepository.findById(id).get();
            int add = comment.like(user);
            User us = userRepository.findUserByUsername(comment.getUser().getUsername());
            us.setKarma(us.getKarma() + add);
            userRepository.save(us);
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    public List<Comment> liked(String username) {
        return commentRepository.getLikedByUsername(username);
    }

    public List<Long> getNewsComments(long id) {
        return this.commentRepository.getNewsComments(id);
    }
}

package com.example.demo.Reply;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }
    public void setNewReply(Reply reply){
        replyRepository.save(reply);
    }

    @Bean
    BCryptPasswordEncoder getByCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

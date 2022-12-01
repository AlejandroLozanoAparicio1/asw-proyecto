package com.example.demo.Utils.DTOs;

import com.example.demo.Commentary.Comment;

public class DTOReply {
    private Long id;
    private Comment comment;

    public DTOReply(Long id, Comment comment) {
        this.id = id;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}


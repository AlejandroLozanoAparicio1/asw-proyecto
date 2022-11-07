package com.example.demo.News;

import com.example.demo.Commentary.Comment;
import com.example.demo.Commentary.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    NewsService() {}

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CommentRepository commRepository;

    public List<News> getNewsList() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));
    }

    public Optional<News> getNews(Long id) {
        return newsRepository.findById(id);
    }

    public void createNews(News news) {
        newsRepository.save(news);
    }

    /*
    public List<Comment> getComments(Long id) {
        return newsRepository.findById(id).get().getComments();
    }

    public void newComment(Long id, Long commentId) {
        Comment comment = commRepository.findById(commentId).orElse(null);
        if (comment != null) newsRepository.findById(id).get().addComment(comment);
    }*/
}

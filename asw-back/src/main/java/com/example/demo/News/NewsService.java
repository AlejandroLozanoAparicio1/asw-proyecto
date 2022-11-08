package com.example.demo.News;

import com.example.demo.Commentary.Comment;
import com.example.demo.Commentary.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    NewsService() {}

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CommentRepository commentRepository;

    public List<News> getNewsList() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));
    }

    public List<News> getNewest() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "datePublished"));
    }

    public Optional<News> getNews(Long id) {
        return newsRepository.findById(id);
    }

    public void createNews(News news) {
        if (news.getLink() != null) news.setType("url");
        else news.setType("ask");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        news.setDatePublished(currentDateTime);
        newsRepository.save(news);
    }


    public List<Comment> getComments(Long id) {
        return newsRepository.findById(id).get().getComments();
    }

    public void newComment(Long id, Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        comment.setTime(currentDateTime);
        commentRepository.save(comment);
        News news = newsRepository.findById(id).get();
        news.addComment(comment);
        newsRepository.save(news);
    }
}

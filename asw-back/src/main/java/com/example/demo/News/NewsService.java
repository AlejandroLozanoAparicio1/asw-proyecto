package com.example.demo.News;

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

    public List<News> getNewsList() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));
    }

    public Optional<News> getNews(Long id) {
        return newsRepository.findById(id);
    }

    public void createNews(News news) {
        newsRepository.save(news);
    }
}

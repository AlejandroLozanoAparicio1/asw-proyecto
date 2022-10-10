package com.example.demo.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    NewsService() {}

    @Autowired
    private NewsRepository newsRepository;

    public List<News> getNews() { return newsRepository.findAll(); }

    /*public News getNew(){
        return new News();
    }*/
}

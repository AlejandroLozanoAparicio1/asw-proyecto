package com.example.demo.News;

import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class NewsController {
    //private NewsService newsService = new NewsService();

    /*
    @GetMapping("getNew")
    public New getNew(){
        return newsService.getNew();
    }
     */

    @Autowired
    NewsService newsService;


    @GetMapping("newslist/get")
    public List<News> getNewsList() {
        return newsService.getNewsList();
    }

    @GetMapping("news/get")
    public Optional<News> getNews(Long id) {
        return newsService.getNews(id);
    }

    @PostMapping("news/post")
    public void getNews(@RequestBody News news) {
        newsService.createNews(news);
    }
}

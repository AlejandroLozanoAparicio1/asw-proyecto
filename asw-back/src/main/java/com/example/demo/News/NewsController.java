package com.example.demo.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @GetMapping("news/get")
    public List<News> getNewss(){
        return newsService.getNews();
    }
}

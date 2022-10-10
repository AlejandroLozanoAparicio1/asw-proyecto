package com.example.demo.News;

import org.springframework.stereotype.Controller;

@Controller
public class NewsController {
    private NewsService newsService = new NewsService();

    /*
    @GetMapping("getNew")
    public New getNew(){
        return newsService.getNew();
    }
     */
}

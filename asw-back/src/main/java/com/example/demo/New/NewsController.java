package com.example.demo.New;

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

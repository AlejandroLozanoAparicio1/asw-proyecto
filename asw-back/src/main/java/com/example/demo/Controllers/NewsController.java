package com.example.demo.Controllers;

import com.example.demo.Service.NewsService;
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

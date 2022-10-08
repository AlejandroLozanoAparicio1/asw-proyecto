package Controllers;

import Models.New;
import Service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {
    private NewsService newsService = new NewsService();

    @GetMapping("getNew")
    public New getNew(){
        return newsService.getNew();
    }
}

package com.example.demo.News;

import com.example.demo.User.HackNewsRepository;
import com.example.demo.User.User;
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
    private HackNewsRepository hkRepository;

    public List<News> getNewsList() {
        return newsRepository.findAll(Sort.by(Sort.Direction.DESC, "points"));
    }

    public Optional<News> getNews(Long id) {
        return newsRepository.findById(id);
    }

    public void createNews(String title, String page, Integer points, String publisher, String datePublished, /*List<Commentary> commentaries,*/String link) {
        if (hkRepository.existsById(publisher)) {
            User user = hkRepository.getReferenceById(publisher);
            newsRepository.save(new News(title, page, points, user, datePublished, link));
        }
    }


    /*public News getNew(){
        return new News();
    }*/
}

package Service;

import Models.New;
import org.springframework.web.bind.annotation.GetMapping;

public class NewsService {

    public New getNew(){
        return new New();
    }
}

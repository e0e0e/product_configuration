package pl.sda.springdemo.quiz;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Question4Controller {

    @DeleteMapping("/path4")
    public String method1() {
        return "question4/delete";
    }

    @PostMapping("/path4")
    public String method2() {
        return "question4/post";
    }
}

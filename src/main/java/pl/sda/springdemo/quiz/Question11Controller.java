package pl.sda.springdemo.quiz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Question11Controller {

    @PostMapping("/path11")
    public String method1(@RequestBody MyBody body) {
        return "question11/post/" + body.getName();
    }
}

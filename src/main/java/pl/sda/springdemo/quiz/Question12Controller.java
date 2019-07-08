package pl.sda.springdemo.quiz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/q12")
class Question12Controller {

    @GetMapping("/path12")
    public String method1() {
        return "question12/get/";
    }

    @GetMapping("/path12/{id}")
    public String method2(@PathVariable Long id) {
        return "question12/get/+id";
    }

    @GetMapping
    public String method3() {
        return "question12/get/get";
    }
}

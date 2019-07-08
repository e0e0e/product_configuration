package pl.sda.springdemo.quiz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
class Question6Controller {

    @PutMapping("/path6/{id}")
    public String method1(@PathVariable(required = false) Long id) {
        return "question6/put/" + id;
    }

    @GetMapping("/path6")
    public String method2() {
        return "question6/get";
    }
}

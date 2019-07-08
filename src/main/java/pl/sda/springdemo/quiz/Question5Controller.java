package pl.sda.springdemo.quiz;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Question5Controller {

    @GetMapping("/path5/{id}")
    public String method1(@RequestParam(required = false) Long id) {
        return "question5/get/"+id;
    }

    @DeleteMapping("/path5")
    public String method2() {
        return "question5/delete";
    }
}

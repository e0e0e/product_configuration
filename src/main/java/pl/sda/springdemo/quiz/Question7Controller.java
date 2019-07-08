package pl.sda.springdemo.quiz;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/q7")
class Question7Controller {

    @GetMapping("/path7/{id}")
    public String method1(@PathVariable Long id, @RequestParam String name) {
        return "question7/get/"+id+"/"+name;
    }

    @DeleteMapping("/path7")
    public String method2() {
        return "question7/delete";
    }
}

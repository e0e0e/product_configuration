package pl.sda.springdemo.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Question3Controller {

    @GetMapping("/path3/{id}")
    public String method1(@PathVariable long id){
        return "question3/get/"+id;
    }

    @GetMapping("/path3")
    public String method2(){
        return "question3/get";
    }
}

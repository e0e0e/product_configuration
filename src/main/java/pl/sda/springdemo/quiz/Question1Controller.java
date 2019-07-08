package pl.sda.springdemo.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class Question1Controller {

    @GetMapping("/path1")
    @ResponseBody
    public String method1(){
        return "question1/get";
    }

    @PostMapping("/path1")
    @ResponseBody
    public String method2(){
        return "question1/post";
    }
}

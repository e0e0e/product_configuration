package pl.sda.springdemo.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class Question2Controller {

    @GetMapping("/path2")
    public String method1(){
        return "question2/get";
    }

    @PostMapping("/path2")
    public String method2(){
        return "question2/post";
    }
}

package pl.sda.springdemo.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
class Question9Controller {

    @GetMapping("/path9")
    public String method1(@RequestParam Long id, @RequestParam String name) {
        return "question9/get/"+id+"_"+name;
    }
}

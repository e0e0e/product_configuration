package pl.sda.springdemo.quiz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Question10Controller {

    @GetMapping("/path10")
    public String method1(@RequestParam Long id, @RequestParam String name) {
        return "question10/get/" + id + "_" + name;
    }

    @GetMapping("/path10/{id}")
    public String method2(@PathVariable Long id, @RequestParam String name) {
        return "question10/get/" + id + ":" + name;
    }

    @GetMapping("/path10/{id}/{name}")
    public String method3(@PathVariable Long id, @PathVariable String name) {
        return "question10/get/" + id + "|" + name;
    }
}

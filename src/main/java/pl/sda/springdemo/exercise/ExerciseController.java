package pl.sda.springdemo.exercise;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.springdemo.CmToInchConverter;

@RestController
public class ExerciseController {

//    @Bean
//    public LanguageResolver languageResolver(){
//        return new LanguageResolver();
//    }


    public MyService myService;

    public CmToInchConverter converter;
    public LanguageController languageController;

    public ExerciseController(@Qualifier("myService1") MyService myService, CmToInchConverter converter) {
        this.myService = myService;
        this.converter=converter;
    }

    @GetMapping("/exe")
    public String printHello() {
        return myService.hello();
    }

    @GetMapping("/convert")
    public double printHello(@RequestParam double cm){
        return converter.convert(cm);
    }



}

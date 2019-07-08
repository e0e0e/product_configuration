package pl.sda.springdemo.exercise;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class LanguageController {

    private String langs;

    public LanguageController(@Value("${app.language:eng}") String lang) {
        this.langs =lang;
    }

    public String getLangs(){
      return langs;
    }

    @GetMapping("/lang")
    public String showLang(){

        String [] languages= langs.split(",");

        return Stream.of(languages)
                .map(l->"- "+l)
                .collect(Collectors.joining("</br>"));
    }
}

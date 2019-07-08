package pl.sda.springdemo.exercise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ExerciseConfiguration {

    @Bean
    LanguageResolver languageResolver() {
        return new LanguageResolver();
    }


    @Bean("myService1")
    @Primary
    public MyService myService1() {


                return ()->"Hi";


    }

    @Bean("myService2")
    public MyService myService2() {
        return new MyService() {
            @Override
            public String hello() {
                return "hola";
            }
        };


    }
}

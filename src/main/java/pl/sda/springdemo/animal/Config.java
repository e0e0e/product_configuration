package pl.sda.springdemo.animal;

import pl.sda.springdemo.hello.HelloServlet;

import javax.servlet.Servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Servlet hello(){
        return new HelloServlet();
    }
}

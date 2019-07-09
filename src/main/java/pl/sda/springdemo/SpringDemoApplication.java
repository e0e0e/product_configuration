package pl.sda.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import pl.sda.springdemo.users.User;

@SpringBootApplication
public class SpringDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
		User user=new User("afd","afd","aafadf","asdf");
		System.out.println(user.getId());
	}
}

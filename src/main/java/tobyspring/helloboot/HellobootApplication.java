package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import tobyspring.config.MySpringBootApplication;

@MySpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너
		SpringApplication.run(HellobootApplication.class, args);

	}
}

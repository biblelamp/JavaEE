package io.springboot.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HelloApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "helloapp");
		SpringApplication.run(HelloApplication.class, args);
	}

}

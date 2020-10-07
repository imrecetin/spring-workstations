package com.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCaching
public class SprintRedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintRedisDemoApplication.class, args);
	}

}

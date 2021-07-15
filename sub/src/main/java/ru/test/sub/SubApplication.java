package ru.test.sub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SubApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubApplication.class, args);
	}

}

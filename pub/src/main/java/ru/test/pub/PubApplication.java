package ru.test.pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubApplication.class, args);
	}

}

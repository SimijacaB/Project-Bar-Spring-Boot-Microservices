package com.microservices.ingredient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicesIngredientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesIngredientApplication.class, args);
	}

}

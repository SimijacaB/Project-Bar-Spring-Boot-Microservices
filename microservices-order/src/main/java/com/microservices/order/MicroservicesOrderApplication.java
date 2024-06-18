package com.microservices.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicesOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesOrderApplication.class, args);
	}

}

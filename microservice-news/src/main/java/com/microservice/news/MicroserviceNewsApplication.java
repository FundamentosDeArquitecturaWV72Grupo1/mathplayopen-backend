package com.microservice.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceNewsApplication.class, args);
	}

}

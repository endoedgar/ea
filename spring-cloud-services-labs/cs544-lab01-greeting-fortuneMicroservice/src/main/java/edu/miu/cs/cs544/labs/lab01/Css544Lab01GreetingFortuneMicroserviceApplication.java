package edu.miu.cs.cs544.labs.lab01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Css544Lab01GreetingFortuneMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Css544Lab01GreetingFortuneMicroserviceApplication.class, args);
	}

}

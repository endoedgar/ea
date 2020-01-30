package edu.miu.cs.cs544.labs.lab01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Css544Lab01GreetingEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Css544Lab01GreetingEurekaServerApplication.class, args);
	}

}

package edu.miu.cs.cs544.labs.lab01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import edu.miu.cs.cs544.labs.lab01.domain.Greeting;
import edu.miu.cs.cs544.labs.lab01.service.FortuneService;

@RestController
public class GreetingController {
	@Autowired
	FortuneService fortuneServiceClient;
	
	@GetMapping("/")
	@HystrixCommand(fallbackMethod = "fallback")
	public Greeting getFortune() {
		return new Greeting("Some text", fortuneServiceClient.getFortune());
	}
	
	public Greeting fallback() {
		return new Greeting("Something bad happened", null);
	}
}

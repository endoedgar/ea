package edu.miu.cs.cs544.labs.lab01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.cs.cs544.labs.lab01.domain.Fortune;
import edu.miu.cs.cs544.labs.lab01.service.FortuneServiceImpl;

@RestController
public class FortuneController {
	private FortuneServiceImpl fortuneService;
	
	public FortuneController(FortuneServiceImpl fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@GetMapping("/")
	public Fortune getFortune() {
		return fortuneService.getFortune();
	}
}

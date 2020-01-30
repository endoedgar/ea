package edu.miu.cs.cs544.labs.lab01.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.miu.cs.cs544.labs.lab01.domain.Fortune;

@Service
public class FortuneServiceClient implements FortuneService {
	private RestTemplate restTemplate;
	
	public FortuneServiceClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public Fortune getFortune() {
		return restTemplate.getForObject("http://FORTUNE/", Fortune.class);
	}
}

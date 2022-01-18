package com.eaa.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private Logger log = LoggerFactory.getLogger(CircuitBreakerController.class);

	
	// @Retry(name = "default") // retry trice before failing default config
	
	// @Retry(name = "sample-api", fallbackMethod = "hardCodeResponse") // sample-api configured in applicaion.yml
	
	//@CircuitBreaker(name = "default", fallbackMethod = "hardCodeResponse") // use @CircuitBreaker instead of @Retry
	
	//@RateLimiter(name = "default") // default allows 1000 requests each 10 seconds. We can override this in application.yml
	
	@Bulkhead(name = "sample-api")
	@GetMapping("/sample-api")
	public String getSample() {

		log.info("Sample Api call received");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dome-dummy-url",
//				String.class);
//		return forEntity.getBody();
		return "Sample-API";
	}

	public String hardCodeResponse(Exception ex) {
		return "fallback-response";
	}

}

package com.eaa.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eaa.microservices.limitsservice.bean.Limits;
import com.eaa.microservices.limitsservice.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration configuration;
	
	@Value("${limits-service.password}")
	private String pwd; 
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		System.out.println("----pwd-----"+pwd);
		//return new Limits(1, 1000);
		return new Limits(configuration.getMin(), configuration.getMax(), configuration.getPassword());
		
	}

}

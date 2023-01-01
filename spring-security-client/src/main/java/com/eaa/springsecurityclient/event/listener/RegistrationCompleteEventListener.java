package com.eaa.springsecurityclient.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.eaa.springsecurityclient.entity.User;
import com.eaa.springsecurityclient.event.RegistrationCompleteEvent;
import com.eaa.springsecurityclient.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>{
	
	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
	   // Create verification token for the user with link
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		userService.saveVerificationTokenForUser(token,user);
	  // Send email to the user
		String url = event.getApplicationUrl() + "verifyRegistration?token="+token;
		log.info("Click link to verify your account: {}", url);
		
	}

}

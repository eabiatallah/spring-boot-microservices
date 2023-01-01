package com.eaa.springsecurityclient.controller;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eaa.springsecurityclient.entity.User;
import com.eaa.springsecurityclient.entity.VerificationToken;
import com.eaa.springsecurityclient.event.RegistrationCompleteEvent;
import com.eaa.springsecurityclient.model.PasswordModel;
import com.eaa.springsecurityclient.model.UserModel;
import com.eaa.springsecurityclient.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/hello")
	public String HelloApi(){
		return "Hello World!";
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
		
		User user = userService.registerUser(userModel);
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
		return "Success";
		
	}
	
	@GetMapping("/verifyRegistration")
	public String verifyRegistration(@RequestParam("token") String token){
		String result = userService.validateVerificationToken(token);
		if(result.equalsIgnoreCase("valid")){
			return "User Verified Successfully";
		}
		else{
			return "Bad User";
		}
		
	}
	
	@GetMapping("/resendVerifyToken")
	public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request){
		VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
		User user = verificationToken.getUser();
		resendVerificationTokenEmail(user, applicationUrl(request), verificationToken);
		return "Verification Link Sent";
		
	}
	
	@PostMapping("/resetPassowrd")
	public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request){
		User user = userService.findUserByEmail(passwordModel.getEmail());
		String url = "";
		if(user!=null){
			String token = UUID.randomUUID().toString();
			userService.createPasswordResetTokenForUser(user, token);
			url = passwordResetTokenEmail(user, applicationUrl(request), token);
		}
		return url;
		
	}
	
	@PostMapping("/savePassword")
	public String savePassword(@RequestParam("token") String token, @RequestBody PasswordModel model){
		
		String result = userService.validatePasswordResetToken(token);
		if(!result.equalsIgnoreCase("valid")){
			return "Invalid Token";
		}
		Optional<User> user = userService.getUserByPasswordResetToken(token);
		if(user.isPresent()){
			userService.changePassword(user.get(), model.getNewPassword());
			return "Password reset successfully";
		} else {
			return "Invalid Token";
		}
		
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestBody PasswordModel passwordModel){
		User user = userService.findUserByEmail(passwordModel.getEmail());
		if(!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())){
			return "Invalid Old Password";
		}
		// Save New password
		userService.changePassword(user, passwordModel.getNewPassword());
		return "Password Changed Successfully";
	}

	private String passwordResetTokenEmail(User user, String applicationUrl, String token) {
		String url = applicationUrl + "savePassword?token="+token;
		log.info("Click link to Reset your password: {}", url);
		return url;
	}

	private void resendVerificationTokenEmail(User user, String applicationUrl, VerificationToken verificationToken) {
		String url = applicationUrl + "verifyRegistration?token="+verificationToken.getToken();
		log.info("Click link to verify your account: {}", url);
		
	}

	private String applicationUrl(HttpServletRequest request) {
		return "http://"+ request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath();
	
	}

}

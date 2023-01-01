package com.eaa.springsecurityclient.service.impl;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eaa.springsecurityclient.entity.PasswordResetToken;
import com.eaa.springsecurityclient.entity.User;
import com.eaa.springsecurityclient.entity.VerificationToken;
import com.eaa.springsecurityclient.model.UserModel;
import com.eaa.springsecurityclient.repository.PasswordResetTokenRepository;
import com.eaa.springsecurityclient.repository.UserRepository;
import com.eaa.springsecurityclient.repository.VerificationTokenRepository;
import com.eaa.springsecurityclient.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo; 
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository; 
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(UserModel userModel) {
		User user = new User();
		user.setEmail(userModel.getEmail());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setRole("USER");
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		userRepo.save(user);
		return user;
	}

	@Override
	public void saveVerificationTokenForUser(String token, User user) {
		VerificationToken verificationToken = new VerificationToken(token, user);
		verificationTokenRepository.save(verificationToken);
		
	}

	@Override
	public String validateVerificationToken(String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
		if(verificationToken == null ){
			return "invalid Token";
		}
		
		User user = verificationToken.getUser();
		Calendar cal =  Calendar.getInstance();
		if (verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
			verificationTokenRepository.delete(verificationToken);
			return "expired";
		}
		user.setEnabled(true);
		userRepo.save(user);
		return "valid";
	}

	@Override
	public VerificationToken generateNewVerificationToken(String oldToken) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
		verificationToken.setToken(UUID.randomUUID().toString());
		verificationTokenRepository.save(verificationToken);
		return verificationToken;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		 PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
		 passwordResetTokenRepository.save(passwordResetToken);
	}

	@Override
	public String validatePasswordResetToken(String token) {
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
		if(passwordResetToken == null ){
			return "invalid Token";
		}
		
		Calendar cal =  Calendar.getInstance();
		if (passwordResetToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
			passwordResetTokenRepository.delete(passwordResetToken);
			return "expired";
		}
		return "valid";
	}

	@Override
	public Optional<User> getUserByPasswordResetToken(String token) {
		 
		return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
	}

	@Override
	public void changePassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepo.save(user);
		
	}

	@Override
	public boolean checkIfValidOldPassword(User user, String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}

}

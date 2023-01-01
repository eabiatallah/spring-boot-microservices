package com.eaa.springsecurityclient.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PasswordResetToken {
	
	    //Expiration Time as 10 minutes
		private static final int EXPIRATION_TIME = 10;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String token;

		private Date expirationTime;

		@OneToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN"))
		private User user;
		
		public PasswordResetToken(String token) {
			super();
			this.token = token;
			this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
		}

		public PasswordResetToken(String token, User user) {
			super();
			this.token = token;
			this.user = user;
			this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
		}

		private Date calculateExpirationDate(int expirationTime) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(new Date().getTime());
			calendar.add(Calendar.MINUTE, expirationTime);
			return new Date(calendar.getTime().getTime());
		}
		
		


}

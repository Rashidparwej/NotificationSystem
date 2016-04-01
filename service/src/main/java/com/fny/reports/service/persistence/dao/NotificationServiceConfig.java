package com.fny.reports.service.persistence.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationServiceConfig {

	/*@Value("rashid.parwej@fashionandyou.com")
	private String notificationEmailTo;*/

	@Value("/emailBody2.vm")
	private String mailVelocityTemplate;

	@Value("ORDER SUMMARY")
	private String emailSubject;                                  

	
	@Value("parwej.rashid7@gmail.com")
	private String notificationMailSender;
	
	public String getMailVelocityTemplate() {
		return mailVelocityTemplate;
	}

	public String getEmailSubject() {
		return emailSubject;
	}


	public String getNotificationMailSender() {
		return notificationMailSender;
	}


}

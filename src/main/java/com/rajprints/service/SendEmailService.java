package com.rajprints.service;

import java.lang.invoke.MethodHandles;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.rajprints.helper.Constants;
import jakarta.mail.internet.MimeMessage;

//IMPORTANT --- This file is subset & merge of EmaillingService project: com.emailingservice.service.SendEmailService.java & com.emailingservice.helper.CommonUtils.java
//@Service			----> uncomment if you want to use this class instead of GmailEmailService
public class SendEmailService {
	
	private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	private static JavaMailSenderImpl javaMailSender = null;
	static {
		prepareJavaMailSender();
	}
	private static void prepareJavaMailSender() {
		javaMailSender = new JavaMailSenderImpl();
		// https://docs.spring.io/spring-boot/docs/2.1.18.RELEASE/reference/html/boot-features-email.html
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("spring.mail.test-connection", "false");
		prop.setProperty("spring.mail.properties.mail.smtp.auth", "true");
		prop.setProperty("spring.mail.properties.mail.smtp.connectiontimeout", "2000");
		prop.setProperty("spring.mail.properties.mail.smtp.timeout", "2000");
		prop.setProperty("spring.mail.properties.mail.smtp.writetimeout", Constants.emailWriteTimeOutInMS);
		prop.setProperty("spring.mail.jndi-name", "mail/Session");
		javaMailSender.setJavaMailProperties(prop);
		
		// set user, pass - it depends upon login
		javaMailSender.setUsername(Constants.fromEmailId);
		javaMailSender.setPassword(Constants.fromEmailIdAuthKey);
		javaMailSender.setProtocol(Constants.emailProtocol);							// "smtp"
		javaMailSender.setHost(Constants.emailHost);									// "smtp.gmail.com", "smtp-mail.outlook.com"
		javaMailSender.setPort(Constants.emailPort);									// 587
		logger.info("Created new JavaMailSenderImpl object=" + javaMailSender);
	}

	public synchronized static void sendHTMLEmailWithoutAttachment(String subject, String htmlEmailBody, String toEmail, Boolean emailOwnerOnFailure) throws InterruptedException {
		
		MimeMessage msg = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);			// true = multipart message
			helper.setFrom(Constants.fromEmailId);									// IMP when sending from Microsoft account
			helper.setTo(toEmail);
			helper.setCc(Constants.fromEmailId);
			helper.setSubject(subject);
			helper.setText(htmlEmailBody, true);
			logger.info("Sending email using JavaMailSenderImpl object=" + javaMailSender);
			javaMailSender.send(msg);
			logger.info("Email sent successfully for=" + toEmail);
		} catch (Exception e) {
			logger.info("FAILED to send email to " + toEmail);
			if(emailOwnerOnFailure) {
				prepareJavaMailSender();
				sendHTMLEmailWithoutAttachment("FAILED to send email to " + toEmail, "Exception occurred while sending email="+e, Constants.fromEmailId, false);		// send					
			} else {
				logger.info("emailOwnerOnFailure=" + emailOwnerOnFailure);
				throw new RuntimeException("Retry Failed, NEED ATTENTION");
			}
		}
		logger.info("Going to sleep for " + Constants.sleepInSecAfterEmailSent + " seconds");
		Thread.sleep(Constants.sleepInSecAfterEmailSent * 1000);
		logger.info("Resuming work after sleep of " + Constants.sleepInSecAfterEmailSent + " seconds");
	}
}
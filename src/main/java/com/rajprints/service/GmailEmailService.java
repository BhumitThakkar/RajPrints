package com.rajprints.service;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.rajprints.helper.Constants;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class GmailEmailService {
	
//	RefreshToken Blog: https://linuxamination.blogspot.com/2022/09/google-api-generate-access-token-and.html
//	RefreshToken: https://www.youtube.com/watch?v=t0RKgHskYwI
//	Oauth2.0: https://www.youtube.com/watch?v=ZV5yTm4pT8g
	private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String USER_ID = "me"; // Use "me" to indicate the authenticated user.
    private static Gmail gmailService;
    private static TokenResponse accessToken;
    private static ZonedDateTime accessTokenExpiresAt;
    private static HttpTransport httpTransport;
    
    public synchronized static Message sendHTMLEmailWithoutAttachment(String subject, String htmlEmailBody, String toEmail, Boolean emailOwnerOnFailure) throws Exception {
		if(httpTransport == null) {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		}
    	if(gmailService == null || isAccessTokenExpired()) {
			prepareGmailService();
		}
    	try {
			return gmailService.users().messages().send(USER_ID, createEmail(toEmail, subject, htmlEmailBody)).execute();
		} catch (Exception e) {
			logger.info("FAILED to send email due to="+e.getMessage(), e);
	    	if(emailOwnerOnFailure) {
	    		prepareGmailService();
	    		return sendHTMLEmailWithoutAttachment("FAILED to send email", "Exception occurred while sending email="+e, Constants.adminEmail, false);
	    	} else {
				logger.info("emailOwnerOnFailure=" + emailOwnerOnFailure);
				throw new RuntimeException("Retry Failed, NEED ATTENTION");
	    	}
		}
    }
    
    private static boolean isAccessTokenExpired() {
    	logger.info("accessTokenExpiresAt="+accessTokenExpiresAt);
    	if(accessTokenExpiresAt.compareTo(ZonedDateTime.now()) < 0) {		// accessTokenExpiresAt is less than current time
    		return true;
    	} else {
    		return false;
    	}
		
	}

    private static Message createEmail(String toEmail, String subject, String htmlEmailBody) throws IOException, MessagingException {
        MimeMessage mimeMessage = createMimeMessage(toEmail, subject, htmlEmailBody);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        mimeMessage.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

    private static MimeMessage createMimeMessage(String toEmail, String subject, String htmlEmailBody) throws MessagingException {
    	Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        InternetAddress fromAddress = new InternetAddress(Constants.adminEmail);
        InternetAddress toAddress = new InternetAddress(toEmail);

        email.setFrom(fromAddress);
        email.addRecipient(RecipientType.BCC, fromAddress);
        email.addRecipient(RecipientType.TO, toAddress);
        email.setSubject(subject);

        // Set HTML content
        MimeMultipart mimeMultipart = new MimeMultipart("alternative");
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText("Please enable HTML to view this message.", "utf-8");
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(htmlEmailBody, "text/html; charset=utf-8");
        mimeMultipart.addBodyPart(textPart);
        mimeMultipart.addBodyPart(htmlPart);
        email.setContent(mimeMultipart);
        return email;
    }
    
    private static void refreshAccessToken(String refreshToken) throws IOException {
    	accessToken = new GoogleRefreshTokenRequest(
    			httpTransport,
                JSON_FACTORY,
                refreshToken,
                Constants.clientId,
                Constants.clientSecret)
                .execute();
    	logger.info("1 hour validity accessToken="+accessToken);
    	accessTokenExpiresAt = ZonedDateTime.now().plusSeconds(accessToken.getExpiresInSeconds()-Constants.refreshTokenBufferInSec);    	
    }

	private static void prepareGmailService() throws Exception {
        refreshAccessToken(Constants.refreshToken);
        // Build Gmail service
        gmailService = new Gmail.Builder(httpTransport, JSON_FACTORY, null)
                .setHttpRequestInitializer(request -> {
                    request.getHeaders().setAuthorization("Bearer " + accessToken.getAccessToken());
                    request.getHeaders().setContentType(MediaType.APPLICATION_JSON.toString());
                })
                .setApplicationName(Constants.apiName)
                .build();
        logger.info("Created new gmailService object=" + gmailService);
    }
}

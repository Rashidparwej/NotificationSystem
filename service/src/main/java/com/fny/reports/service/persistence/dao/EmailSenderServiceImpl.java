package com.fny.reports.service.persistence.dao;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

	private static final Log logger = LogFactory.getLog(EmailSenderServiceImpl.class);

	private static String host = "smtp.gmail.com";
	private static String port = "587";
	private static String username = "parwej.rashid7@gmail.com";
	private static String password = "PARWEJarshad6@";
	private static String auth = "true";                     

	@Override                                                       
	public void send(final String to, final String subject, final String fromEmailId, final String body)
			throws Exception {                                

		sendMail(to, null, null, subject, body, fromEmailId);    

		logger.info("Sent mail to " + to);
	}

	private boolean sendMail(String to, List<String> cc, List<String> bcc, String subject, String messageBodyFinal,
			String from) {
		boolean send = sendEmail(host, port, username, password, auth, from, to, cc, bcc, subject, messageBodyFinal);
		return send;
	}

	private boolean sendEmail(String host, String portval, final String username, final String password, String auth,
			String from, String to, List<String> cc, List<String> bcc, String subject, String htmlContent) {
		try {

			int port = Integer.parseInt(portval.trim());

			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);      
			props.put("mail.smtp.auth", "true"); //enable authentication
	        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

			Authenticator authenticator = null;                      
			boolean isAuth = Boolean.valueOf(auth);
			if (isAuth) {
				props.put("mail.smtp.auth", true);                     
				authenticator = new Authenticator() {            
					
					private PasswordAuthentication pa = new PasswordAuthentication(username, password);

					@Override                                 
					public PasswordAuthentication getPasswordAuthentication() {
						return pa;
					}
				};
			}
			Session session = Session.getInstance(props, authenticator);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			InternetAddress addressTo = new InternetAddress(to);
			message.setRecipient(Message.RecipientType.TO, addressTo);
			if (cc != null) {
				for (String addressCc : cc) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(addressCc));
				}
			}
			if (bcc != null) {                                
				for (String addressBcc : bcc) {              
					message.addRecipient(Message.RecipientType.BCC, new InternetAddress(addressBcc));
				}                                                  
			}                                                    
			message.setSubject(subject);                  
			message.setSentDate(new Date());
			Multipart multipart = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlContent, "text/html");
			multipart.addBodyPart(htmlPart);
			message.setContent(multipart);
			Transport.send(message);

			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
package com.ba.utils;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.mail.Session; 
import javax.mail.Transport; 


public class SendEmail {


	public void send(String from,String to,String subject,String content){
		try {
			String host = "127.0.0.1"; 
			Properties properties = System.getProperties(); 
			properties.setProperty("mail.smtp.host", host); 
			Session session = Session.getDefaultInstance(properties); 
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress(from)); 
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
			message.setSubject(subject); 
			message.setText(content); 
			Transport.send(message); 
			System.out.println("Mail successfully sent"); 

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

} 

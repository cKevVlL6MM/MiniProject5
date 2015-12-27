package com.controllers;
import com.beans.*;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/newsletter")
public class NewsletterController {
	
	@RequestMapping(method = RequestMethod.POST)
	protected void sendEmail(@ModelAttribute("mail")Mail mail, ModelMap model){
		model.addAttribute("mail", mail);
		System.out.println(mail.getContenu());
		Date today=new Date();
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yy");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("jobeisti@gmail.com","jobeisti2k17");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jobeisti@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("gaspardjrm@eisti.eu"));
			message.setSubject("Newsletter du "+date.format(today)+" : "+mail.getSujet());
			message.setContent("<h1>"+mail.getContenu()+"</h1>", "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
}

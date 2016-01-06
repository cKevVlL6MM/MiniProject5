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

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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

			@SuppressWarnings("deprecation")
			SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
			//SessionFactory sessionFactory = createSessionFactory();
			org.hibernate.Session sessions= sf.openSession();
			ArrayList<String> liste=new ArrayList<String>();
			Query query;
			if(mail.isCpi1()){
				query=sessions.createSQLQuery("select email from Table_Eleve e, Table_Classe c where  c.NOMCLASSE='cpi1' and c.IDCLASSE=e.IDCLASSE");
				liste.addAll(query.list());
			}
			if(mail.isCpi2()){
				query=sessions.createSQLQuery("select email from Table_Eleve e, Table_Classe c where  c.NOMCLASSE='cpi2' and c.IDCLASSE=e.IDCLASSE");
				liste.addAll(query.list());
			}
			if(mail.isIng1()){
				query=sessions.createSQLQuery("select email from Table_Eleve e, Table_Classe c where  c.NOMCLASSE='ing1' and c.IDCLASSE=e.IDCLASSE");
				liste.addAll(query.list());
			}
			if(mail.isIng2()){
				query=sessions.createSQLQuery("select email from Table_Eleve e, Table_Classe c where  c.NOMCLASSE='ing2' and c.IDCLASSE=e.IDCLASSE");
				liste.addAll(query.list());
			}
			if(mail.isIng3()){
				query=sessions.createSQLQuery("select email from Table_Eleve e, Table_Classe c where  c.NOMCLASSE='ing3' and c.IDCLASSE=e.IDCLASSE");
				liste.addAll(query.list());
			}
			
		Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jobeisti@gmail.com"));
			for (int i=0;i<liste.size();i++) {
				message.addRecipients(Message.RecipientType.TO,	InternetAddress.parse(liste.get(i)));
			}
			message.setSubject("Newsletter du "+date.format(today)+" : "+mail.getSujet());
			message.setContent(mail.getContenu()+"<br/><img src=\"<c:url value=\"img/LOGO.png\"/>\"/>", "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	@RequestMapping(method = RequestMethod.GET)
	public static String loginRedirect() {
		
		
		return "Login";
	}
	
	
}

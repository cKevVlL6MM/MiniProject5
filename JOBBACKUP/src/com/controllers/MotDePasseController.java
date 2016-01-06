package com.controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Registration;

@Controller
@RequestMapping(value="/motdepasse")
public class MotDePasseController {
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView redirection(ModelMap model,HttpServletRequest request){
		return new ModelAndView("MotDePasse");
	}
	
	public static BigDecimal convertIntToBD(int integer)
    {
     return  new BigDecimal( (double)integer );
    }
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView reinitMDP(ModelMap model,HttpServletRequest request) {
		
		SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		org.hibernate.Session sessions= sf.openSession();
		Query querytestEleve=sessions.createSQLQuery("select IDUSER from Table_Eleve where email='"+request.getParameter("email")+"'");
		Query querytestEntreprise=sessions.createSQLQuery("select IDUSER from Table_Entreprises where email='"+request.getParameter("email")+"'");
		String mdp=new BigInteger(130,  new SecureRandom()).toString(32);
		Query query;
		
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
		java.math.BigDecimal id=convertIntToBD(-1);
		if(!querytestEleve.list().isEmpty()){
			id=(BigDecimal) querytestEleve.list().get(0);
		}
		else if(!querytestEntreprise.list().isEmpty()) {
			id=(BigDecimal) querytestEntreprise.list().get(0);		
		}
	
		if((!querytestEleve.list().isEmpty())||(!querytestEntreprise.list().isEmpty())) {
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("jobeisti@gmail.com"));
				message.addRecipients(Message.RecipientType.TO,	InternetAddress.parse(request.getParameter("email")));
				message.setSubject("Mot de passe oublié");
				message.setContent("<h3>Voici votre nouveau mot de passe :<br/>"+mdp+"</h3><br/><img src=\"<c:url value=\"img/LOGO.png\"/>", "text/html; charset=utf-8");

				Transport.send(message);
				System.out.println(id);
				Transaction transac = sessions.beginTransaction();
				query=sessions.createSQLQuery("update Table_Login set PASSWORD=:mdp where IDUSER=:id");
				query.setParameter("id", id);
				query.setParameter("mdp", mdp);
				query.executeUpdate();
				transac.commit();
				request.setAttribute("messageSuccess", "Merci de consulter votre boîte mail");	
				System.out.println("Done");


				} catch (MessagingException e) {
				throw new RuntimeException(e);
				}
		}
		else {
			request.setAttribute("messageError", "Adresse mail non valide");
		}
		return new ModelAndView("Login");
	}
}

package com.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Registration;

import hibernate.model.*; 


@Controller
@RequestMapping(value="/registration")
public class InscriptionController  {
	
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	/*	
	@RequestMapping(method = RequestMethod.GET)
	   public ModelAndView student() {
	    Registration usermodel=new Registration();
	      return new ModelAndView("registration", "registration",usermodel);
	   }
	*/
	@RequestMapping(method = RequestMethod.POST)
	protected void inscriptionValidation(@ModelAttribute("registration") Registration user, ModelMap model) {
		// TODO Auto-generated method stub
		model.addAttribute("registration", user);
		System.out.println("identifiant : "+user.getIdentifiant());
		System.out.println("password : "+user.getPassword());
		System.out.println("nom : "+user.getNom());
		System.out.println("prenom : "+user.getPrenom());
		System.out.println("age : "+user.getAge());
		System.out.println("telephone : "+user.getTelephone());
		System.out.println("classe : "+user.getClasse());
		
		if((user.getIdentifiant()=="")||(user.getPassword()=="")||(user.getNom()=="")||(user.getClasse()=="")||(user.getPrenom()=="")){
			
		}
		
		
		else {
			int classe;
			switch(user.getClasse()){
			case "cpi1":
				classe=1;
				break;
			case "cpi2":
				classe=2;
				break;
			case "ing1":
				classe=3;
				break;
			case "ing2":
				classe=4;
				break;
			case "ing3":
				classe=5;
				break;
			default:
				classe=0;
				break;
			}

			@SuppressWarnings("deprecation")
			SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
			//SessionFactory sessionFactory = createSessionFactory();
			Session sessions= sf.openSession();
			Query query=sessions.getNamedQuery("creer_eleve");
			query.setParameter("pNOM", user.getNom());		
			query.setParameter("pPRENOM", user.getPrenom());
			query.setParameter("pIDCLASSE",classe);
			query.setParameter("pCIVILITE", user.getCivilite());
			query.setParameter("pAGE", user.getAge());
			query.setParameter("pTELEPHONE", user.getTelephone());
			query.setParameter("pEMAIL", user.getEmail());
			query.setParameter("pIDENTIFIANT",user.getIdentifiant());
			query.setParameter("pMOTDEPASSE", user.getPassword());
			List list=query.list();
			sessions.close();	
		
		}
		
		
	}
	
	
	public static SessionFactory createSessionFactory() {
	     Configuration configuration = new Configuration();
	     configuration.configure();
	     serviceRegistry = new ServiceRegistryBuilder().applySettings(
	            configuration.getProperties()). buildServiceRegistry();
	     sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	     return sessionFactory;
	}
	

}
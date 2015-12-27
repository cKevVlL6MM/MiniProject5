package com.controllers;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.HibernateUtil;
import com.beans.Registration;

import hibernate.model.*;

@Controller
@RequestMapping(value="/SignIn")


public class LoginController {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	
	
	public static String CheckRole(BigDecimal id) 
	
	{
		SessionFactory sf =  HibernateUtil.getSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		//Query query = sessions.getNamedQuery("LISTE_LOGINS");
		
		Query query=sessions.createQuery("from UserRole where IDUSER=:tags").setParameter("tags", id);
		UserRole res = (UserRole) query.list().get(0);
		
		
		
		return res.getRoles();
		
		
	
		
		
		
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public static String Proceed(@ModelAttribute("SignIn") TableLogin logins, ModelMap model)
	{
		model.addAttribute("SignIn", logins);
		int EnteredId=Integer.valueOf(logins.getIdentifiant());
		String EnteredPassword=logins.getPassword();
		BigDecimal cId = new BigDecimal(EnteredId);
		
		
		Boolean isValid=false;
		String Result = CheckRole(cId) ;
		
		
			
			System.out.println(Result) ;
			
		
		
		
		return  Result;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}

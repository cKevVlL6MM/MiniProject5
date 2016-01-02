package com.controllers;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	private static final String roleEleve ="6mww5lakiz8w69yoswzb";
	private static final String roleEntreprise="ra2mqscru3i95k55cfne";
	private static final String roleAdmin="cgo9dbyvqmsrl8m72jvw";
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	
	
	public static String CheckRole(String identifiant, String mdp,HttpServletRequest request) 
	
	{
		
		SessionFactory sf =  HibernateUtil.getSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		//Query query = sessions.getNamedQuery("LISTE_LOGINS");
		
		
		
		
			//HQL ne supporte pas les jointures entre deux tables externes
		Query query=sessions.createSQLQuery("select r.* from Table_Login l LEFT JOIN User_Role r on l.IDUSER=r.IDUSER Where identifiant = :ide and password = :pwd")
				.addEntity(UserRole.class)
				.setParameter("ide", identifiant)
				.setParameter("pwd", mdp);
		
		
		
		if(!query.list().isEmpty())
		{
			
			
		
			
			UserRole res =  (UserRole) query.list().get(0);
			request.getSession().setAttribute("UserId", res.getIduser());
			
			
			
			
			return res.getRoles();
		}
		
		
		
		
		
			return "invalid";
		
		
	
		
		
		
	}
	
	public static BigDecimal CheckValidity(String pId, String pPwd)
	{
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session sessions = sf.openSession();
		TableLogin res=new TableLogin();
		
		
		Query query=sessions.createSQLQuery("from TableLogin where (identifiant=:tags and password=:pwd)").setParameter("tags", pId).setParameter("pwd", pPwd);
		if(!query.list().isEmpty())
		{
		 res = (TableLogin) query.list().get(0);
		}
		return res.getIduser();
		
		
		
	}
	
	
	
public static String Redirect(String role)


{
	//System.out.println(role);
	if(role.equals(roleEleve))
	{
		return "AccueilEleve";
	}
	else if(role.equals(roleEntreprise))
	{
		return "AccueilEntreprise";
	}
	else if(role.equals(roleAdmin))
	{
		return "AccueilAdmin";
	}
	else 
	{
		return "Login";
	}
	
	
	
}
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public static String Proceed(@ModelAttribute("SignIn") TableLogin logins, ModelMap model,HttpServletRequest request)
	{
		model.addAttribute("SignIn", logins);
		
		String EnteredId=logins.getIdentifiant();
		String EnteredPassword=logins.getPassword();
		
		
		
		
		String Result = CheckRole(EnteredId,EnteredPassword,request) ;
		
		
		
			
		
		
		
		return  Redirect(Result);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

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
import com.beans.ProfileUtilisateur;
import com.beans.Registration;

import hibernate.model.*;

@Controller
@RequestMapping(value="/SignIn")


public class LoginController {
	
	private static final String roleEleve ="6mww5lakiz8w69yoswzb";
	private static final String roleEntreprise="ra2mqscru3i95k55cfne";
	private static final String roleAdmin="cgo9dbyvqmsrl8m72jvw";
	private static BigDecimal UserId;
	
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public static String doGet(HttpServletRequest request)
	{
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		if(pl!=null)
		{
			if(pl.isEleve() || pl.isAdmin() || pl.isEnterprise())
			{
				return Redirect(pl.getRoleUID(),request);
			}
		}
		return "Login";
		
		
	}
	
	
	
	
	
	public static String CheckRole(String identifiant, String mdp,HttpServletRequest request) 
	
	{
		
		
		SessionFactory sf =  HibernateUtil.getSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		//Query query = sessions.getNamedQuery("LISTE_LOGINS");
		

			//HQL ne supporte pas les jointures entre deux tables externes nous utiliserons des requêtes SQL.
		Query query=sessions.createSQLQuery("select r.* from Table_Login l LEFT JOIN User_Role r on l.IDUSER=r.IDUSER Where identifiant = :ide and password = :pwd")
				.addEntity(UserRole.class)
				.setParameter("ide", identifiant)
				.setParameter("pwd", mdp);
		
		
		
		if(!query.list().isEmpty())
		{
			
			
		
			
			UserRole res =  (UserRole) query.list().get(0);
			setUserId(res.getIduser());
			
			
			
			
			
			//request.getSession().setAttribute("UserId", res.getIduser());
			
			
			
			
			return res.getRoles();
		}
		
		
		
		
		
			return "invalid";
		
		
	
		
		
		
	}
	
	
	
	
public static String Redirect(String role, HttpServletRequest request)


{
	TableUtilisateurs tu = ProfileUtilisateur.getTableUtilisateur(getUserId());
	
	
	//System.out.println(role);
	if(role.equals(roleEleve))
	{
		
		ProfileUtilisateur profilEleve = new ProfileUtilisateur(tu,role);
		
		
		request.getSession().setAttribute("profileutilisateur", profilEleve);
		
		return "AccueilEleve";
	}
	else if(role.equals(roleEntreprise))
	{
		ProfileUtilisateur profilEntreprise = new ProfileUtilisateur(tu,role);
		
		request.getSession().setAttribute("profileutilisateur",profilEntreprise);
		
		return "AccueilEntreprises";
	}
	else if(role.equals(roleAdmin))
	{
		ProfileUtilisateur profilAdmin = new ProfileUtilisateur(tu,role);
		request.getSession().setAttribute("profileutilisateur", profilAdmin);
		
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
		
		if(EnteredId!=null && EnteredPassword !=null)
		
		{
		String Result = CheckRole(EnteredId,EnteredPassword,request) ;
		
		//permet a Result.equals de ne pas retourner une exception null
		
		if(!Result.equals("invalid"))
		{
		return  Redirect(Result,request);
		}
		
		
		}
		
			return "Login";
		
		
	}




	private static BigDecimal getUserId() {
		return UserId;
	}




	private static void setUserId(BigDecimal userId) {
		UserId = userId;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

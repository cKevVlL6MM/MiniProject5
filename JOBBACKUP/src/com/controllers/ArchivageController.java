package com.controllers;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.HibernateUtil;
import com.beans.ProfileUtilisateur;

@Controller
@RequestMapping(value="/Archivage")

public class ArchivageController {

	
	public static BigDecimal convertIntToBD(int integer)
	 {
		
	  return  new BigDecimal( (double)integer );
	 }

	
	@RequestMapping(method = RequestMethod.GET)
	public static String doGet(HttpServletRequest request)
	{
		
		
		
ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		
		if( pl!=null && ( pl.isAdmin() || pl.isEleve() || pl.isEnterprise()))
		{
		
			BigDecimal idOffreSelected=convertIntToBD(Integer.parseInt((String) request.getParameter("idoffrechoisie")));
		

			SessionFactory sf =  HibernateUtil.getSessionFactory();
			//SessionFactory sessionFactory = createSessionFactory();
			Session sessions= sf.openSession();
			
			Query query=sessions.createSQLQuery("CALL ARCHIVER_ANNONCE( :iduser, :idannonce)")
			.setParameter("iduser", pl.getId())
			.setParameter("idannonce", idOffreSelected);		
			query.executeUpdate();
			
			
		
		
		
		
		return "redirect:annonces";
		
		}
		return "Login";
		
	}
	
	
	
	
	
	
	
}

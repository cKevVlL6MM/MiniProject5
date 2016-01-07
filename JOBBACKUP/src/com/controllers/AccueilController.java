package com.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.ProfileUtilisateur;

import hibernate.model.TableEvenements;

@Controller
@RequestMapping(value="/Accueil")


public class AccueilController {
	
	@RequestMapping(method = RequestMethod.GET)
	public static String doGet(HttpServletRequest request)
	{
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		Session sessions = sf.openSession();
		Query query = sessions.createSQLQuery("Select * FROM Table_Evenements WHERE datefin >= sysdate ORDER BY datedebut asc").addEntity(TableEvenements.class);
		
		ArrayList<TableEvenements> listEvents = new ArrayList<TableEvenements>();
		for (int i=0;i<query.list().size();i++){
			listEvents.add((TableEvenements) query.list().get(i));
		}
		request.setAttribute("listEvents", listEvents);
		
		
		if(pl.isEleve())
		{
			return "AccueilEleve";
		}
		else if(pl.isAdmin())
		{
			return "AccueilAdmin";
		}
		else if(pl.isEnterprise())
		{
			return "AccueilEntreprises";
		}
		else
		{
			return "Login";
			
		}
		
		
		
		
	}

}

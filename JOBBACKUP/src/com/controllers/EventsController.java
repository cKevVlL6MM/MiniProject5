package com.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.*;

import hibernate.model.TableEvenements;

@Controller
@RequestMapping(value="/events")
public class EventsController {
	
	@RequestMapping(method = RequestMethod.GET)
	public static ModelAndView printEvents(HttpServletRequest request)
	{
		
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");

		if (pl != null && (pl.isEleve() || pl.isAdmin() || pl.isEnterprise()))
		{
			SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
			Session sessions = sf.openSession();
			Query query = sessions.createSQLQuery("Select * FROM Table_Evenements WHERE datefin >= sysdate ORDER BY datedebut asc").addEntity(TableEvenements.class);
			
			ArrayList<TableEvenements> listEvents = new ArrayList<TableEvenements>();
			for (int i=0;i<query.list().size();i++){
				listEvents.add((TableEvenements) query.list().get(i));
			}

	
			return new ModelAndView("PrintEvents", "listEvents", listEvents);
		}
		
		String messageError = "La consultation des évènements est reservée aux élèves";
		request.setAttribute("messageError", messageError);
		return new ModelAndView("Login");
	}
	
	
}

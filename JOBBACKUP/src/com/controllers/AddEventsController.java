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
@RequestMapping(value="/addEvent")
public class AddEventsController {
	
	@RequestMapping(method = RequestMethod.GET)
	public static ModelAndView doGet(HttpServletRequest request)
	{
		
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");

		if (pl != null && pl.isAdmin())
		{
			return new ModelAndView("AddEvent");
		}
		
		String messageError = "La création des évènements est reservée aux administrateurs";
		request.setAttribute("messageError", messageError);
		return new ModelAndView("Login");
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView addEvent(@ModelAttribute("tableevenements")TableEvenements event, ModelMap model, HttpServletRequest request){
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");

		if (pl != null && pl.isAdmin())
		{
			model.addAttribute("tableevenements", event);
			@SuppressWarnings("deprecation")
			SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
			//SessionFactory sessionFactory = createSessionFactory();
			Session sessions= sf.openSession();
			sessions.getTransaction().begin();
			Query query=sessions.createSQLQuery("INSERT INTO TABLE_EVENEMENTS (idevent, nomevenement, datedebut, datefin) VALUES (SQ_IDEVENT.NEXTVAL, :pNOMEVENEMENT, :pDATEDEBUT, :pDATEFIN) ");
			query.setParameter("pNOMEVENEMENT", event.getNomevenement());
			query.setDate("pDATEDEBUT", event.getDatedebut());
			query.setDate("pDATEFIN", event.getDatefin());
			query.executeUpdate();
			sessions.getTransaction().commit();

			
			Query query2 = sessions.createSQLQuery("Select * FROM Table_Evenements WHERE datefin >= sysdate ORDER BY datedebut asc").addEntity(TableEvenements.class);
			
			ArrayList<TableEvenements> listEvents = new ArrayList<TableEvenements>();
			if(!query2.list().isEmpty()){
				for (int i=0;i<query2.list().size();i++){
					listEvents.add((TableEvenements) query2.list().get(i));
				}
				request.setAttribute("listEvents", listEvents);
			}
			
			return new ModelAndView("AccueilAdmin");
		}
		
		String messageError = "La création des évènements est reservée aux administrateurs";
		request.setAttribute("messageError", messageError);
		return new ModelAndView("Login");
				
	}	
}

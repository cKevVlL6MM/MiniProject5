package com.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.ProfileUtilisateur;

import hibernate.model.TableOffres;
import hibernate.model.TableOffresAttentes;

@Controller	
public class AnnoncesAdminController {
	
	public static BigDecimal convertIntToBD(int integer)
    {
     return  new BigDecimal( (double)integer );
    }

	@RequestMapping(value="/gererAnnonceAdmin",method = RequestMethod.GET)
	protected ModelAndView redirAnnonces (HttpServletRequest request){
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		if((pl==null)||(!pl.isAdmin())){
			return new ModelAndView("Login");
		}
		else {
			SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
			//SessionFactory sessionFactory = createSessionFactory();
			Session sessions= sf.openSession();
			Query query=sessions.createSQLQuery("select * from Table_Offres").addEntity(TableOffres.class);
			Query queryAttentes=sessions.createSQLQuery("select * from Table_Offres_Attentes").addEntity(TableOffresAttentes.class);
			ArrayList<TableOffres> offres=new ArrayList<TableOffres>();
			if (!query.list().isEmpty()) {
				for (int i=0;i<query.list().size();i++){
					offres.add((TableOffres) query.list().get(i));
				}
				request.setAttribute("nbValide",query.list().size() );
			}
			if (!queryAttentes.list().isEmpty()) {
				for (int i=0;i<queryAttentes.list().size();i++){
					offres.add((TableOffresAttentes) queryAttentes.list().get(i));
				}
			}
			request.setAttribute("nbValide",query.list().size() );
			if(request.getParameter("valeur")!=null){
				request.setAttribute("valeur",request.getParameter("valeur"));
			}
			sessions.close();
			return new ModelAndView("AnnonceAdmin","listeOffres", offres);
		}
	}
	
	//@RequestMapping(value="/validAnnonceController",method = RequestMethod.POST)
	

	@RequestMapping(value="/refusAnnonceController",method = RequestMethod.POST)
	protected ModelAndView refAnnonces (@ModelAttribute("tableoffres")TableOffres offre, ModelMap model,HttpServletRequest request){
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		model.addAttribute("tableoffres", offre);
		@SuppressWarnings("deprecation")
		SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		Transaction transac = sessions.beginTransaction();
		int id=Integer.parseInt((String) request.getParameter("valeur"));
		Query query3=sessions.createSQLQuery("select * from TABLE_OFFRES where IDOFFRE=:id");
		query3.setParameter("id", convertIntToBD(id));
		if(!query3.list().isEmpty()) {
			Query query=sessions.createSQLQuery("delete from TABLE_OFFRES where IDOFFRE=:id");
			query.setParameter("id", convertIntToBD(id));
			query.executeUpdate();
		}
		else {
			Query query2=sessions.createSQLQuery("delete from TABLE_OFFRES_ATTENTES where IDOFFRE=:id");
			query2.setParameter("id", convertIntToBD(id));
			query2.executeUpdate();
		}
		transac.commit();
		sessions.close();
		request.setAttribute("valeur",null);
		return new ModelAndView("redirect:gererAnnonceAdmin");
	}
}

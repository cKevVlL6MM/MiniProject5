package com.controllers;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.SimpleTimeZone;

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
public class AnnonceEntrepriseController {
	
	public static BigDecimal convertIntToBD(int integer)
    {
     return  new BigDecimal( (double)integer );
    }
	
	@RequestMapping(value="/creerAnnonceController",method = RequestMethod.POST)
	protected ModelAndView creerAnnonce(@ModelAttribute("tableoffres")TableOffresAttentes offre, ModelMap model, HttpServletRequest request){
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		model.addAttribute("tableoffresattentes", offre);
		@SuppressWarnings("deprecation")
		SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		Query query=sessions.createSQLQuery("call CREER_ANNONCE (:pIDENTREPRISE, :pIDTYPECONTRAT,:pIDTYPESECTEUR,:pIDTYPECOMPETENCE ,:pIDNIVEAUMINIMUM, :pTITREOFFRE ,:pCONTENU ,:pDUREEOFFRE )");
		query.setParameter("pIDENTREPRISE",pl.getEnterpriseInfo().getIdentreprise());
		query.setParameter("pIDTYPECONTRAT", offre.getIdtypecontrat());
		query.setParameter("pIDTYPESECTEUR", pl.getEnterpriseInfo().getIdtypesecteur());
		query.setParameter("pIDTYPECOMPETENCE",convertIntToBD(1));
		query.setParameter("pIDNIVEAUMINIMUM", offre.getIdniveauminimum());
		query.setParameter("pTITREOFFRE", offre.getTitreoffre());
		query.setParameter("pCONTENU", offre.getContenu());
		query.setParameter("pDUREEOFFRE", offre.getDureeoffre());
		query.executeUpdate();	
		sessions.close();
		return new ModelAndView("redirect:modifAnnonceController");
	}	
	
	@RequestMapping(value="/modifAnnonceController",method = RequestMethod.POST)
	protected ModelAndView modifAnnonce(@ModelAttribute("tableoffres")TableOffres offre, ModelMap model, HttpServletRequest request){
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		model.addAttribute("tableoffres", offre);
		@SuppressWarnings("deprecation")
		SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		Transaction transac = sessions.beginTransaction();
		int id=Integer.parseInt((String) request.getParameter("idOffreActuel"));
		Query query=sessions.createSQLQuery("update TABLE_OFFRES set TITREOFFRE='"+offre.getTitreoffre()+"',CONTENU='"+offre.getContenu()+"',IDTYPECONTRAT='"+offre.getIdtypecontrat()+"',DUREEOFFRE=:date,IDNIVEAUMINIMUM='"+offre.getIdniveauminimum()+"' where IDOFFRE=:id");
		query.setDate("date", offre.getDureeoffre());
		query.setParameter("id", convertIntToBD(id));
		query.executeUpdate();
		transac.commit();
		sessions.close();
		request.setAttribute("valeur",null);
		return new ModelAndView("redirect:modifAnnonceController");
	}
	
	@RequestMapping(value="/supAnnonceController",method = RequestMethod.GET)
	protected ModelAndView supAnnonces (@ModelAttribute("tableoffres")TableOffres offre, ModelMap model,HttpServletRequest request){
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		model.addAttribute("tableoffres", offre);
		@SuppressWarnings("deprecation")
		SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		Transaction transac = sessions.beginTransaction();
		int id=Integer.parseInt((String) request.getParameter("valeur"));
		Query query=sessions.createSQLQuery("delete from TABLE_OFFRES where IDOFFRE=:id");
		query.setParameter("id", convertIntToBD(id));
		query.executeUpdate();
		transac.commit();
		sessions.close();
		request.setAttribute("valeur",null);
		return new ModelAndView("redirect:modifAnnonceController");
	}
	
	
	@RequestMapping(value="/modifAnnonceController",method = RequestMethod.GET)
	protected ModelAndView redirAnnonces (HttpServletRequest request){
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		if((pl==null)||(!pl.isEnterprise())){
			return new ModelAndView("Login");
		}
		else {
			SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
			//SessionFactory sessionFactory = createSessionFactory();
			Session sessions= sf.openSession();
			Query query=sessions.createSQLQuery("select * from Table_Offres where IDENTREPRISE='"+pl.getEnterpriseInfo().getIdentreprise()+"'").addEntity(TableOffres.class);
			Query queryAttentes=sessions.createSQLQuery("select * from Table_Offres_Attentes where IDENTREPRISE='"+pl.getEnterpriseInfo().getIdentreprise()+"'").addEntity(TableOffresAttentes.class);
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
			return new ModelAndView("MesAnnonces","listeOffres", offres);
		}
	}
	
}

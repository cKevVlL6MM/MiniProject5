package com.controllers;

import java.math.BigDecimal;

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

import com.beans.ProfileUtilisateur;

import hibernate.model.TableOffres;

@Controller			
public class AnnonceEntrepriseController {
	
	public static BigDecimal convertIntToBD(int integer)
    {
     return  new BigDecimal( (double)integer );
    }
	
	@RequestMapping(value="/creerAnnonceController",method = RequestMethod.POST)
	protected void creerAnnonce(@ModelAttribute("tableoffres")TableOffres offre, ModelMap model, HttpServletRequest request){
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		model.addAttribute("tableoffres", offre);
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
				
	}	
		
	@RequestMapping(value="/modifierAnnonceController",method = RequestMethod.POST)
	protected String modifierAnnonce(@ModelAttribute("tableoffres")TableOffres offre, ModelMap model, HttpServletRequest request){
		request.setAttribute("modif"+request.getAttribute("valeur"),1 );
		return "MesAnnonces";
	}
	
	@RequestMapping(value="/modifAnnonceController",method = RequestMethod.POST)
	protected void modifAnnonce(@ModelAttribute("tableoffres")TableOffres offre, ModelMap model, HttpServletRequest request){
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		model.addAttribute("tableoffres", offre);
		@SuppressWarnings("deprecation")
		SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		Query query=sessions.createSQLQuery("update TABLE_OFFRES set TITREOFFRE='"+offre.getTitreoffre()+"',CONTENU='"+offre.getContenu()+"',IDTYPEDECONTRAT='"+offre.getIdtypecontrat()+"',DUREEOFFRE='"+offre.getDureeoffre()+"',IDNIVEAUMINIMUM='"+offre.getIdniveauminimum()+"' where IDOFFFRE='"+request.getParameter("idOffreActuel")+"'");
		query.executeUpdate();
		sessions.close();	
		request.setAttribute("modif"+request.getAttribute("valeur"),0 );		
	}	
}

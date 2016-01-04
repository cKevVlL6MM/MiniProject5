package com.controllers;

import java.io.IOException;
import java.math.BigDecimal;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.Registration;

import hibernate.model.*; 


@Controller
@RequestMapping(value="/registration")
public class InscriptionController  {
	
	public static BigDecimal convertIntToBD(int integer)
    {
     return  new BigDecimal( (double)integer );
    }
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	/*	
	@RequestMapping(method = RequestMethod.GET)
	   public ModelAndView student() {
	    Registration usermodel=new Registration();
	      return new ModelAndView("registration", "registration",usermodel);
	   }
	*/
	@RequestMapping(method = RequestMethod.POST)
	protected void inscriptionValidation(@ModelAttribute("registration") Registration user, ModelMap model) {
		// TODO Auto-generated method stub
		model.addAttribute("registration", user);
		
		SessionFactory sf =  new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		Query querytestEleve=sessions.createSQLQuery("select count(*) from Table_Eleve where email='"+user.getEmail()+"'");
		Query querytestEntreprise=sessions.createSQLQuery("select count(*) from Table_Entreprise where email='"+user.getEmail()+"'");
		BigDecimal test;
		if((user.getFax()=="")&&(user.getPrenom()!="")){
			test=(BigDecimal) querytestEleve.uniqueResult();
			if(test.intValue()<1){
			int classe;
			switch(user.getClasse()){
			case "cpi1":
				classe=1;
				break;
			case "cpi2":
				classe=2;
				break;
			case "ing1":
				classe=3;
				break;
			case "ing2":
				classe=4;
				break;
			case "ing3":
				classe=5;
				break;
			default:
				classe=0;
				break;
			}

			Query query=sessions.createSQLQuery("call CREER_ELEVE(:pNOM,:pPRENOM,:pIDCLASSE,:pCIVILITE,:pTELEPHONE,:pAGE,:pEMAIL,:pIDENTIFIANT,:pMOTDEPASSE)");
			query.setParameter("pNOM", user.getNom());		
			query.setParameter("pPRENOM", user.getPrenom());
			query.setParameter("pIDCLASSE",convertIntToBD(classe));
			query.setParameter("pCIVILITE", user.getCivilite());
			query.setParameter("pAGE", user.getAge());
			query.setParameter("pTELEPHONE", user.getTelephone());
			query.setParameter("pEMAIL", user.getEmail());
			query.setParameter("pIDENTIFIANT",user.getUsername());
			query.setParameter("pMOTDEPASSE", user.getPassword());
			query.executeUpdate();
			sessions.close();	
			}
			
		}
		else if((user.getAdresse()!=null)&&(user.getPrenom()==null)) {
			test=(BigDecimal) querytestEntreprise.uniqueResult();
			if(test.intValue()<1){
			int type;
			switch(user.getTypeSecteur()){
			case "Informatique":
				type=1;
				break;
			case "Aeronautique":
				type=2;
				break;
			case "Mecanique":
				type=3;
				break;
			case "Agriculture":
				type=4;
				break;
			case "Automobile":
				type=5;
				break;
			case "Finance":
				type=6;
				break;
			case "Assurance":
				type=7;
				break;
			case "Alimentation":
				type=8;
				break;
			case "Design":
				type=9;
				break;
			default:
				type=10;
				break;
				
			}
			
			Query query=sessions.createSQLQuery("call CREER_ENTREPRISE(:pNOMSOCIETE,:pADRESSE,:pTYPESECTEUR,:pFAX,:pTELEPHONE,:pCODEPOSTAL,:pEMAIL,:pIDENTIFIANT,:pMOTDEPASSE)");
			query.setParameter("pNOMSOCIETE", user.getNom());		
			query.setParameter("pADRESSE", user.getAdresse());
			query.setParameter("pTYPESECTEUR", convertIntToBD(type));
			query.setParameter("pFAX", user.getFax());
			query.setParameter("pCODEPOSTAL", user.getCodePostal());
			query.setParameter("pEMAIL", user.getEmail());
			query.setParameter("pIDENTIFIANT",user.getUsername());
			query.setParameter("pMOTDEPASSE", user.getPassword());
			query.setParameter("pTELEPHONE", user.getTelephone());
			query.executeUpdate();
			sessions.close();	
			}
		}
		
		
	}
	
	
	public static SessionFactory createSessionFactory() {
	     Configuration configuration = new Configuration();
	     configuration.configure();
	     serviceRegistry = new ServiceRegistryBuilder().applySettings(
	            configuration.getProperties()). buildServiceRegistry();
	     sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	     return sessionFactory;
	}
	

}
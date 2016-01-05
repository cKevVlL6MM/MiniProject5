package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.ProfileUtilisateur;

@Controller
@RequestMapping(value="/Accueil")


public class AccueilController {
	
	@RequestMapping(method = RequestMethod.GET)
	public static String doGet(HttpServletRequest request)
	{
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		
		
		
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

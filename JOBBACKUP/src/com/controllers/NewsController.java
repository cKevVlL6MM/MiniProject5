package com.controllers;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beans.*;

@Controller
@RequestMapping(value="/news")
public class NewsController {
	
	@RequestMapping(method = RequestMethod.GET)
	public static String printAllNews(HttpServletRequest request)
	{
		ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		
		if(pl.isAdmin() || pl.isEleve() || pl.isEnterprise())
		{
			return "annonces";
		}
		else
		{
			return "Login";
		}
		}

}

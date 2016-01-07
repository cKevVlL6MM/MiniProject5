package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/deconnexion")

public class DeconnexionController {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public static String doGet(HttpServletRequest request)
	{
		
		 request.getSession().setAttribute("profileutilisateur",null);
		
		
		
		
		
		
		
		
		return "Login";
		
		
	}
	
	
	

}

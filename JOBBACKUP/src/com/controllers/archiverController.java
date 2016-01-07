package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping(value="/Archiver")
	
public class archiverController {

	@RequestMapping(method = RequestMethod.GET)
	public static String doGet(HttpServletRequest request)
	{
		
		
		
		
		return "annonces";
		
		
	}
	
	
	
	
}

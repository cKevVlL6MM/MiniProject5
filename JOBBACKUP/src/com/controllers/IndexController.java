package com.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller





@RequestMapping("/hello")


public class IndexController {
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	   public String printHello(ModelMap model) {
		
	      model.addAttribute("message", "Hello MVC world!");
	      return "hello";

}
}

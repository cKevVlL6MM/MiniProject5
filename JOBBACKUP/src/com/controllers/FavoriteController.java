package com.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.HibernateUtil;
import com.beans.ProfileUtilisateur;

import hibernate.model.TableOffres;

@Controller
@RequestMapping(value="/favorites")

public class FavoriteController {

	public static <T> ArrayList<T> fill (List<T> l)
	{
		
		if(!l.isEmpty())
		{	
			ArrayList<T> al = new ArrayList();
		for(int i=0;i<l.size();i++)
		{
			al.add(l.get(i));
		}
		return al;
		}
		else
		{
			return null;
		}

	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public static ModelAndView doGet(HttpServletRequest request)
	{
		
		
		
ProfileUtilisateur pl = (ProfileUtilisateur)  request.getSession().getAttribute("profileutilisateur");
		
		if( pl!=null && pl.isEleve() )
		{
			if(pl.getIdAnnoncesArchivees()!=null && !pl.getIdAnnoncesArchivees().isEmpty())
			{
				SessionFactory sf =  HibernateUtil.getSessionFactory();
				//SessionFactory sessionFactory = createSessionFactory();
				Session sessions= sf.openSession();
				
				pl.getIdAnnoncesArchivees().get(0);

				Query query=sessions.createQuery("FROM TableOffres WHERE IDOFFRE in :id ")
						.setParameterList("id", pl.getIdAnnoncesArchivees());
				
				if(!query.list().isEmpty())
				{
				ArrayList<TableOffres> listefavorites = fill( query.<TableOffres>list() );
				
				
				return new ModelAndView("favorites","listeFavorites",listefavorites);
				}
				
				
			}
			
			
			
		}
		
		return new ModelAndView("favorites");
	}
	
	
}

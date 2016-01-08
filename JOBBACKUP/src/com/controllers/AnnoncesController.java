package com.controllers;

import java.math.BigDecimal;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

import com.beans.HibernateUtil;
import com.beans.ProfileUtilisateur;
import com.beans.Recherche;
import com.beans.RechercheListee;

import hibernate.model.TableClasse;
import hibernate.model.TableEntreprises;
import hibernate.model.TableLogin;
import hibernate.model.TableOffres;
import hibernate.model.TableSecteur;
import hibernate.model.TableTypesContrat;
import hibernate.model.UserRole;


@Controller
@RequestMapping(value="/annonces")
public class AnnoncesController {
	
	
	private static final String roleEleve ="6mww5lakiz8w69yoswzb";
	private static final String roleEntreprise="ra2mqscru3i95k55cfne";
	private static final String roleAdmin="cgo9dbyvqmsrl8m72jvw";
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		
		if( pl!=null && ( pl.isAdmin() || pl.isEleve() || pl.isEnterprise()))
		{
			
			SessionFactory sf =  HibernateUtil.getSessionFactory();
			//SessionFactory sessionFactory = createSessionFactory();
			Session sessions= sf.openSession();
			
			Query query=sessions.createQuery("FROM TableOffres");
			
			
			if(!query.list().isEmpty())
			{
			
					
				ArrayList<RechercheListee> listeAnnonces = new ArrayList<RechercheListee>();
				ArrayList<TableOffres> listeOffres = new ArrayList<TableOffres>();
				
				
				
				listeOffres= fill  (query.<TableOffres>list());	
				
				
				
				
				
				
				
				int nombreOffres = listeOffres.size();
				
				// On remplit la arraylist recherchelistee
				
				for(int r=0;r<nombreOffres;r++)
				{
					RechercheListee a = new RechercheListee();
					a.setOffre(listeOffres.get(r));
					listeAnnonces.add(a);
				   
				}
				
				
				
				
				
				
				// les Identreprises des offres trouv�es sont stock�es l�
				BigDecimal[] ArrayIDE = new BigDecimal[nombreOffres];
				
				//array des idtypesecteurs des offres
				BigDecimal[] ArrayIDS = new BigDecimal[nombreOffres];
				
				//array des idtypesContrat cf sch�ma mld
				BigDecimal[] ArrayIDC = new BigDecimal[nombreOffres];
				
				//array des idniveauminimum
				BigDecimal[] ArrayINM = new BigDecimal[nombreOffres];
				
				
				//on remplit les array des id, nous chercherons � trouver les noms des secteurs d'activit� � partir des idtypesecteur
				for(int i=0; i<nombreOffres; i++)
				{
					
					ArrayIDE[i]=listeOffres.get(i).getIdentreprise();
					System.out.println("identreprise "+ArrayIDE[i].toString());
					ArrayIDS[i]=listeOffres.get(i).getIdtypesecteur();
					ArrayIDC[i]=listeOffres.get(i).getIdtypecontrat();
					System.out.println("identreprise "+ArrayIDE[i].toString());
					ArrayINM[i]=listeOffres.get(i).getIdniveauminimum();
				}
				
				Query queryIDE = sessions.createQuery("FROM TableEntreprises where IDENTREPRISE IN :ide ").setParameterList("ide", ArrayIDE);
				
				//on v�rifie toujours qu'on manipule des listes non vides 
				if(!queryIDE.list().isEmpty())
				{
					
					for(int j=0; j<queryIDE.list().size(); j++)
					{
						RechercheListee b = new RechercheListee();
					
							b=listeAnnonces.get(j);
							TableEntreprises te = (TableEntreprises) queryIDE.list().get(j);
							b.setEnterprise(te);
							
							
							listeAnnonces.set(j, b);
							
							
						
										
					}
						
					
				}
				
				Query queryIDS = sessions.createQuery("FROM TableSecteur where IDTYPESECTEUR IN :ids ").setParameterList("ids", ArrayIDS);
				
				if(!queryIDS.list().isEmpty())
				{
					
					for(int l=0; l<queryIDS.list().size(); l++)
					{
						RechercheListee b2 = new RechercheListee();
						if(l<listeAnnonces.size())
						{
							b2=listeAnnonces.get(l);
							TableSecteur ts = (TableSecteur) queryIDS.list().get(l);
							
							String NomSecteur = ts.getNomsecteur();
							b2.setNomSecteur(NomSecteur);
							listeAnnonces.set(l, b2);
							
							
						}
										
					}
						
					
				}
				
				Query queryIDC = sessions.createQuery("FROM TableTypesContrat where Idtypecontrat IN :idc ").setParameterList("idc", ArrayIDC);
				
				if(!queryIDC.list().isEmpty())
				{
					
					for(int m=0; m<queryIDC.list().size(); m++)
					{
						RechercheListee b3 = new RechercheListee();
						if(m<listeAnnonces.size())
						{
							b3=listeAnnonces.get(m);
							TableTypesContrat ttc = (TableTypesContrat)  queryIDC.list().get(m);
							
							String NomTypeContrat = ttc.getNomtypecontrat();
							b3.setNomTypeContrat(NomTypeContrat);
							listeAnnonces.set(m, b3);
							
							
						}
										
					}
						
					
				}
				Query queryINM = sessions.createQuery("FROM TableClasse where IDCLASSE IN :inm ").setParameterList("inm", ArrayINM);
				
				if(!queryINM.list().isEmpty())
				{
					
					for(int n=0; n<queryINM.list().size(); n++)
					{
						RechercheListee b4 = new RechercheListee();
						if(n<listeAnnonces.size())
						{
							b4=listeAnnonces.get(n);
							TableClasse tcl = (TableClasse) queryINM.list().get(n);
							
							String NomClasse = tcl.getNomclasse();
							b4.setNomClasse(NomClasse);
							listeAnnonces.set(n, b4);
							
							
						}
										
					}
						
					
				}
				
				
				
				
				
				
				
				return new ModelAndView("annonces","listeAnnonces",listeAnnonces);
				}
	
		}
		else
		{
			return new ModelAndView("annonces");
		}
		return new ModelAndView("Login");
		}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public static ModelAndView checkRecherche(@ModelAttribute("annonces") Recherche search, ModelMap model){
		
		SessionFactory sf =  HibernateUtil.getSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		//Query query = sessions.getNamedQuery("LISTE_LOGINS");
		
		
		model.addAttribute("annonces", search);
		//debug
		//System.out.println(search.getTitreoffre()+","+ search.getDatepublication()+","+ search.getIdtypecompetence()+","+search.getIdtypecontrat()+","+search.getIdtypesecteur());
		
		
		String to = search.getTitreoffre();
		if(to.equals("") || to==null )
		{
			to=null;
		}
		
		
		int inm = search.getIdniveauminimum();
		inm=-1;
		int itc = search.getIdtypecontrat();
		int its = search.getIdtypesecteur();
		Date dc = search.getDatepublication();
		Date dd = search.getDureeoffre();
		System.out.println(to+" ");
		System.out.println(itc+" "+inm+" "+its+dc+dd);
		ArrayList<RechercheListee> listeAnnonces = new ArrayList<RechercheListee>();
		ArrayList<TableOffres> listeOffres = new ArrayList<TableOffres>();
		
		
		/* en fonction des champs renseign�s, la requete SQL changera, nous avons 5 param�tres de recherche */
		
		if(to == null && inm==-1 && itc ==-1 && its == -1 && dc == null && dd == null )
		{
			
			return new ModelAndView("annonces");
		}
		else if(to!=null && inm==-1 && itc ==-1 && its == -1 && dc == null && dd == null )
		{
			System.out.println("inside");
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE LIKE :to ")
			.addEntity(TableOffres.class)
			.setParameter("to",to);
			
			
			/*
			Query query2 = sessions.createSQLQuery(
					"CALL LISTE_LOGINS(:identifiant, :mdp)")
					.addEntity(TableLogin.class)
					.setParameter("identifiant", "delin123")
					.setParameter("mdp","12345");
					TableLogin tl = (TableLogin) query2.list().get(0);
			System.out.println(tl.getIdentifiant()); 
			
			*/
			
			
			
			
			
			
			
			listeOffres= fill  (query.<TableOffres>list());
			
		}
		else if(to==null && inm!=-1 && itc ==-1 && its == -1 && dc == null && dd == null )
		{
			BigDecimal binm = convertIntToBD(inm);
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm);
					
					listeOffres= fill  (query.<TableOffres>list());
			
			
		}
		else if(to==null && inm==-1 && itc !=-1 && its == -1 && dc == null && dd == null)
		{
			BigDecimal bitc = convertIntToBD(itc);
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPECONTRAT = :itc ")
					.addEntity(TableOffres.class)
					.setParameter("itc", bitc);
					
					listeOffres= fill  (query.<TableOffres>list());
			
			
		}
		else if(to==null && inm==-1 && itc ==-1 && its != -1 && dc == null && dd == null)
		{
			
			BigDecimal bits = convertIntToBD(its);
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPECONTRAT = :its ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits);
					
					listeOffres= fill  (query.<TableOffres>list());
			
			
			
			
		}
		else if(to==null && inm==-1 && itc ==-1 && its == -1 && dc != null && dd == null)
		{
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where DATEPUBLICATION = :dc ")
					.addEntity(TableOffres.class)
					.setParameter("dc", dc);
					
					listeOffres= fill  (query.<TableOffres>list());
		
		}
		else if(to==null && inm==-1 && itc ==-1 && its == -1 && dc == null && dd != null)
		{
			Query query=sessions.createSQLQuery(" select * from Table_offres where DUREEOFFRE = :dd ")
					.addEntity(TableOffres.class)
					.setParameter("dd", dd);
					
					listeOffres= fill  (query.<TableOffres>list());
		}
		else if(to!=null && inm!=-1 && itc ==-1 && its == -1 && dc == null && dd == null)
		{
			BigDecimal binm = convertIntToBD(inm);
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE  = :to and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("inm", binm);
					
					listeOffres= fill  (query.<TableOffres>list());
		}
		else if(to!=null && inm==-1 && itc !=-1 && its == -1 && dc == null && dd == null)
		{
			BigDecimal bitc = convertIntToBD(itc);
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE  = :to and IDTYPECONTRAT = :itc ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("itc", bitc);
					
					listeOffres= fill  (query.<TableOffres>list());
			
			
			
		}
		else if(to!=null && inm==-1 && itc ==-1 && its != -1 && dc == null && dd == null)
		{
			BigDecimal bits = convertIntToBD(its);
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE  = :to and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("its", bits);
					
					listeOffres= fill  (query.<TableOffres>list());
			
			
			
		}
		else if(to!=null && inm==-1 && itc ==-1 && its == -1 && dc != null && dd == null)
		{
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE  = :to and DATEPUBLICATION = :dc ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("dc", dc);
					
					listeOffres= fill  (query.<TableOffres>list());
			
			
			
		}
		else if(to!=null && inm==-1 && itc ==-1 && its == -1 && dc == null && dd != null)
		{
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE  = :to and DUREEOFFRE = :dd ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("dd", dd);
					
					listeOffres= fill  (query.<TableOffres>list());
			
			
			
		}
		
		
		else if(to==null && inm!=-1 && itc !=-1 && its == -1 && dc == null && dd == null)
		{
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDNIVEAUMINIMUM  = :inm and IDTYPECONTRAT = :itc ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("itc", bitc);
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc ==-1 && its != -1 && dc == null && dd == null)
		{
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bits = convertIntToBD(its);
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDNIVEAUMINIMUM  = :inm and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("its", bits);
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc ==-1 && its == -1 && dc != null && dd == null)
		{
			BigDecimal binm = convertIntToBD(inm);
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDNIVEAUMINIMUM  = :inm and DATEPUBLICATION = :dc")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("dc", dc);
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc ==-1 && its == -1 && dc == null && dd != null)
		{
			BigDecimal binm = convertIntToBD(inm);
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDNIVEAUMINIMUM  = :inm and DUREEOFFRE = :dd")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("dd", dd);
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc !=-1 && its != -1 && dc == null && dd == null)
		{
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPECONTRAT = :itc and IDTYPESECTEUR = :its")
					.addEntity(TableOffres.class)
					.setParameter("inm", bitc)
					.setParameter("its", bits);
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc !=-1 && its == -1 && dc != null && dd == null)
		{
			BigDecimal bitc = convertIntToBD(itc);
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPECONTRAT = :itc and DATEPUBLICATION = :dc")
					.addEntity(TableOffres.class)
					.setParameter("inm", bitc)
					.setParameter("dc", dc);
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc !=-1 && its == -1 && dc == null && dd != null)
		{
			BigDecimal bitc = convertIntToBD(itc);
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPECONTRAT = :itc and DUREEOFFRE = :dd")
					.addEntity(TableOffres.class)
					.setParameter("inm", bitc)
					.setParameter("dd", dd);
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc ==-1 && its != -1 && dc != null && dd == null)
		{
			BigDecimal bits = convertIntToBD(its);
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPESECTEUR = :its and  DATEPUBLICATION = :dc")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("dc", dc);
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc ==-1 && its != -1 && dc == null && dd != null)
		{
			BigDecimal bits = convertIntToBD(its);
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPESECTEUR = :its and  DUREEOFFRE = :dd")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("dd", dd);
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc ==-1 && its == -1 && dc != null && dd != null)
		{
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where DATEPUBLICATION = :dc and  DUREEOFFRE = :dd")
					.addEntity(TableOffres.class)
					.setParameter("dc", dc)
					.setParameter("dd", dd);
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		
		
		
		// 3 occurrences
		
		else if(to!=null && inm!=-1 && itc !=-1 && its == -1 && dc == null && dd == null)
		{
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE = :to and  IDNIVEAUMINIMUM = :inm  and IDTYPECONTRAT = :itc ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("inm", binm)
					.setParameter("itc", bitc);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc ==-1 && its != -1 && dc == null && dd == null)
		{
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bits = convertIntToBD(its);
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE = :to and  IDNIVEAUMINIMUM = :inm  and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("inm", binm)
					.setParameter("its", bits);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc ==-1 && its == -1 && dc != null && dd == null)
		{
			BigDecimal binm = convertIntToBD(inm);
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE = :to and  IDNIVEAUMINIMUM = :inm  and DATEPUBLICATION = :dc ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("inm", binm)
					.setParameter("dc", dc);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc ==-1 && its == -1 && dc == null && dd != null)
		{
			BigDecimal binm = convertIntToBD(inm);
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE = :to and  IDNIVEAUMINIMUM = :inm  and DUREEOFFRE = :dd ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("inm", binm)
					.setParameter("dd", dd);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc !=-1 && its != -1 && dc == null && dd == null)
		{
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDNIVEAUMINIMUM = :inm and IDTYPECONTRAT = :itc  and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("itc", bitc)
					.setParameter("its", bits);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc !=-1 && its == -1 && dc != null && dd == null)
		{
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDNIVEAUMINIMUM = :inm and IDTYPECONTRAT = :itc  and DATEPUBLICATION = :dc ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("itc", bitc)
					.setParameter("dc", dc);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc !=-1 && its == -1 && dc == null && dd != null)
		{
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDNIVEAUMINIMUM = :inm and IDTYPECONTRAT = :itc  and DUREEOFFRE = :dd ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("itc", bitc)
					.setParameter("dd", dd);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc !=-1 && its != -1 && dc != null && dd == null)
		{
			
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPECONTRAT = :itc and IDTYPESECTEUR = :its  and DATEPUBLICATION= :dc ")
					.addEntity(TableOffres.class)
					.setParameter("itc", bitc)
					.setParameter("its", bits)
					.setParameter("dc", dc);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc !=-1 && its != -1 && dc == null && dd != null)
		{
			
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPECONTRAT = :itc and IDTYPESECTEUR = :its  and DUREEOFFRE= :dd ")
					.addEntity(TableOffres.class)
					.setParameter("itc", bitc)
					.setParameter("its", bits)
					.setParameter("dd", dd);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm==-1 && itc !=-1 && its != -1 && dc == null && dd == null)
		{
			
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPECONTRAT = :itc and IDTYPESECTEUR = :its  and TITREOFFRE = :to ")
					.addEntity(TableOffres.class)
					.setParameter("itc", bitc)
					.setParameter("its", bits)
					.setParameter("to", to);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc ==-1 && its != -1 && dc != null && dd != null)
		{
			
			BigDecimal bits = convertIntToBD(its);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPESECTEUR = :its  and DATEPUBLICATION = :dc and DUREEOFFRE = :dd ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("dc", dc)
					.setParameter("dd", dd);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc ==-1 && its != -1 && dc != null && dd == null)
		{
			
			BigDecimal bits = convertIntToBD(its);
			BigDecimal binm = convertIntToBD(inm);
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPESECTEUR = :its  and DATEPUBLICATION = :dc and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("dc", dc)
					.setParameter("inm", binm);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm==-1 && itc ==-1 && its != -1 && dc != null && dd == null)
		{
			
			BigDecimal bits = convertIntToBD(its);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where IDTYPESECTEUR = :its  and DATEPUBLICATION = :dc and TITREOFFRE = :to ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("dc", dc)
					.setParameter("to", to);
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm==-1 && itc ==-1 && its == -1 && dc != null && dd != null)
		{
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where DATEPUBLICATION = :dc and DUREEOFFRE = :dd and TITREOFFRE = :to ")
					.addEntity(TableOffres.class)
					.setParameter("dc", dc)
					.setParameter("dd", dd)
					.setParameter("to", to);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc ==-1 && its == -1 && dc != null && dd != null)
		{
			
			
			BigDecimal binm = convertIntToBD(inm);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where DATEPUBLICATION = :dc and DUREEOFFRE = :dd and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("dc", dc)
					.setParameter("dd", dd)
					.setParameter("inm", binm);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc !=-1 && its == -1 && dc != null && dd != null)
		{
			
			
			BigDecimal bitc = convertIntToBD(itc);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where DATEPUBLICATION = :dc and DUREEOFFRE = :dd and IDTYPECONTRAT = :itc ")
					.addEntity(TableOffres.class)
					.setParameter("dc", dc)
					.setParameter("dd", dd)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm==-1 && itc !=-1 && its == -1 && dc == null && dd != null)
		{
			
			
			BigDecimal bitc = convertIntToBD(itc);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE = :to and DUREEOFFRE = :dd and IDTYPECONTRAT = :itc ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("dd", dd)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm==-1 && itc ==-1 && its != -1 && dc == null && dd != null)
		{
			
			
			BigDecimal bits = convertIntToBD(its);
			
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE = :to and DUREEOFFRE = :dd and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("dd", dd)
					.setParameter("its", bits);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc !=-1 && its != -1 && dc == null && dd == null)
		{
			
			
			BigDecimal bits = convertIntToBD(its);
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE = :to and IDTYPESECTEUR = :its and IDTYPECONTRAT = :itc and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("inm", binm)
					.setParameter("its", bits)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc !=-1 && its == -1 && dc != null && dd == null)
		{
			
			
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE = :to and DATEPUBLICATION = :dc and IDTYPECONTRAT = :itc and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("inm", binm)
					.setParameter("dc", dc)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc !=-1 && its == -1 && dc == null && dd != null)
		{
			
			
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE = :to and DUREEOFFRE= :dd and IDTYPECONTRAT = :itc and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("to", to)
					.setParameter("inm", binm)
					.setParameter("dd", dd)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc !=-1 && its != -1 && dc != null && dd == null)
		{
			
			
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where DATEPUBLICATION= :dc and IDTYPECONTRAT = :itc and IDNIVEAUMINIMUM = :inm and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("inm", binm)
					.setParameter("dc", dc)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc !=-1 && its != -1 && dc == null && dd != null)
		{
			
			
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where DUREEOFFRE= :dd and IDTYPECONTRAT = :itc and IDNIVEAUMINIMUM = :inm and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("inm", binm)
					.setParameter("dd", dd)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm==-1 && itc !=-1 && its != -1 && dc != null && dd != null)
		{
			
			
			
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where DUREEOFFRE= :dd and DATEPUBLICATION = :dc and IDTYPECONTRAT = :itc and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("dc", dc)
					.setParameter("dd", dd)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm==-1 && itc !=-1 && its != -1 && dc != null && dd == null)
		{
			
			
			
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE LIKE :to and DATEPUBLICATION = :dc and IDTYPECONTRAT = :itc and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("dc", dc)
					.setParameter("to","%"+ to+"%")
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm==-1 && itc ==-1 && its != -1 && dc != null && dd != null)
		{
			
			
			
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE LIKE :to and DATEPUBLICATION = :dc and DUREEOFFRE = :dd and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("dc", dc)
					.setParameter("to","%"+ to+"%")
					.setParameter("dd", dd);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc ==-1 && its == -1 && dc != null && dd != null)
		{
			
			
			
		
			BigDecimal binm = convertIntToBD(inm);
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE LIKE :to and DATEPUBLICATION = :dc and DUREEOFFRE = :dd and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("dc", dc)
					.setParameter("to","%"+ to+"%")
					.setParameter("dd", dd);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc ==-1 && its == -1 && dc != null && dd != null)
		{
			
			
			
		
			BigDecimal binm = convertIntToBD(inm);
			
			
			Query query=sessions.createSQLQuery(" select * from Table_offres where TITREOFFRE LIKE :to and DATEPUBLICATION = :dc and DUREEOFFRE = :dd and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("dc", dc)
					.setParameter("to","%"+ to+"%")
					.setParameter("dd", dd);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc !=-1 && its!= -1 && dc != null && dd == null)
		{
			
			
			
		
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			Query query=sessions.createSQLQuery("select * from Table_offres where TITREOFFRE LIKE :to and DATEPUBLICATION = :dc and IDTYPESECTEUR = :its and IDTYPECONTRAT = :itc and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("dc", dc)
					.setParameter("to","%"+ to+"%")
					.setParameter("its", bits)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to==null && inm!=-1 && itc !=-1 && its!= -1 && dc != null && dd != null)
		{
			
			
			
		
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			Query query=sessions.createSQLQuery("select * from Table_offres where DUREEOFFRE = :dd and DATEPUBLICATION = :dc and IDTYPESECTEUR = :its and IDTYPECONTRAT = :itc and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("dc", dc)
					.setParameter("dd",dd)
					.setParameter("its", bits)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc !=-1 && its!= -1 && dc == null && dd != null)
		{
			
			
			
		
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			Query query=sessions.createSQLQuery("select * from Table_offres where TITREOFFRE LIKE :to and DUREEOFFRE = :dd and IDTYPESECTEUR = :its and IDTYPECONTRAT = :itc and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("dd", dd)
					.setParameter("to","%"+ to+"%")
					.setParameter("its", bits)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc !=-1 && its== -1 && dc != null && dd != null)
		{
			
			
			
		
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			//BigDecimal bits = convertIntToBD(its);
			
			
			Query query=sessions.createSQLQuery("select * from Table_offres where TITREOFFRE LIKE :to and DUREEOFFRE = :dd and DATEPUBLICATION = :dc and IDTYPECONTRAT = :itc and IDNIVEAUMINIMUM = :inm ")
					.addEntity(TableOffres.class)
					.setParameter("inm", binm)
					.setParameter("dd", dd)
					.setParameter("to","%"+ to+"%")
					.setParameter("dc", dc)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm==-1 && itc !=-1 && its!= -1 && dc != null && dd != null)
		{
			
			
			
		
			//BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			Query query=sessions.createSQLQuery("select * from Table_offres where TITREOFFRE LIKE :to and DUREEOFFRE = :dd and DATEPUBLICATION = :dc and IDTYPECONTRAT = :itc and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("dd", dd)
					.setParameter("to","%"+ to+"%")
					.setParameter("dc", dc)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc ==-1 && its!= -1 && dc != null && dd != null)
		{
			
			
			
		
			BigDecimal binm = convertIntToBD(inm);
			//BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			Query query=sessions.createSQLQuery("select * from Table_offres where TITREOFFRE LIKE :to and DUREEOFFRE = :dd and DATEPUBLICATION = :dc and IDNIVEAUMINIMUM = :inm and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("dd", dd)
					.setParameter("to","%"+ to+"%")
					.setParameter("dc", dc)
					.setParameter("inm", binm);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else if(to!=null && inm!=-1 && itc !=-1 && its!= -1 && dc != null && dd != null)
		{
			
			
			
		
			BigDecimal binm = convertIntToBD(inm);
			BigDecimal bitc = convertIntToBD(itc);
			BigDecimal bits = convertIntToBD(its);
			
			
			Query query=sessions.createSQLQuery("select * from Table_offres where TITREOFFRE LIKE :to and IDNIVEAUMINIMUM = :inm and DUREEOFFRE = :dd and DATEPUBLICATION = :dc and IDTYPECONTRAT = :itc and IDTYPESECTEUR = :its ")
					.addEntity(TableOffres.class)
					.setParameter("its", bits)
					.setParameter("inm", binm)
					.setParameter("dd", dd)
					.setParameter("to","%"+ to+"%")
					.setParameter("dc", dc)
					.setParameter("itc", bitc);
			
				
					
					listeOffres= fill  (query.<TableOffres>list());	
		}
		else
		{
			//faut mettre une offre vide ici en gros titre offre = aucune annonce
			//System.out.println("inside else");
			listeOffres=null;
		}
		
		
		if( listeOffres != null && !listeOffres.isEmpty() )
		{
			
			
			
			
		int nombreOffres = listeOffres.size();
		
		// On remplit la arraylist recherchelistee
		
		for(int r=0;r<nombreOffres;r++)
		{
			RechercheListee a = new RechercheListee();
			a.setOffre(listeOffres.get(r));
			listeAnnonces.add(a);
		   
		}
		
		
		
		
		
		
		// les Identreprises des offres trouv�es sont stock�es l�
		BigDecimal[] ArrayIDE = new BigDecimal[nombreOffres];
		
		//array des idtypesecteurs des offres
		BigDecimal[] ArrayIDS = new BigDecimal[nombreOffres];
		
		//array des idtypesContrat cf sch�ma mld
		BigDecimal[] ArrayIDC = new BigDecimal[nombreOffres];
		
		//array des idniveauminimum
		BigDecimal[] ArrayINM = new BigDecimal[nombreOffres];
		
		
		//on remplit les array des id, nous chercherons � trouver les noms des secteurs d'activit� � partir des idtypesecteur
		for(int i=0; i<nombreOffres; i++)
		{
			
			ArrayIDE[i]=listeOffres.get(i).getIdentreprise();
			System.out.println("identreprise "+ArrayIDE[i].toString());
			ArrayIDS[i]=listeOffres.get(i).getIdtypesecteur();
			ArrayIDC[i]=listeOffres.get(i).getIdtypecontrat();
			System.out.println("identreprise "+ArrayIDE[i].toString());
			ArrayINM[i]=listeOffres.get(i).getIdniveauminimum();
		}
		
		Query queryIDE = sessions.createQuery("FROM TableEntreprises where IDENTREPRISE IN :ide ").setParameterList("ide", ArrayIDE);
		
		//on v�rifie toujours qu'on manipule des listes non vides 
		if(!queryIDE.list().isEmpty())
		{
			
			for(int j=0; j<queryIDE.list().size(); j++)
			{
				RechercheListee b = new RechercheListee();
			
					b=listeAnnonces.get(j);
					TableEntreprises te = (TableEntreprises) queryIDE.list().get(j);
					b.setEnterprise(te);
					
					
					listeAnnonces.set(j, b);
					
					
				
								
			}
				
			
		}
		
		Query queryIDS = sessions.createQuery("FROM TableSecteur where IDTYPESECTEUR IN :ids ").setParameterList("ids", ArrayIDS);
		
		if(!queryIDS.list().isEmpty())
		{
			
			for(int l=0; l<queryIDS.list().size(); l++)
			{
				RechercheListee b2 = new RechercheListee();
				if(l<listeAnnonces.size())
				{
					b2=listeAnnonces.get(l);
					TableSecteur ts = (TableSecteur) queryIDS.list().get(l);
					
					String NomSecteur = ts.getNomsecteur();
					b2.setNomSecteur(NomSecteur);
					listeAnnonces.set(l, b2);
					
					
				}
								
			}
				
			
		}
		
		Query queryIDC = sessions.createQuery("FROM TableTypesContrat where Idtypecontrat IN :idc ").setParameterList("idc", ArrayIDC);
		
		if(!queryIDC.list().isEmpty())
		{
			
			for(int m=0; m<queryIDC.list().size(); m++)
			{
				RechercheListee b3 = new RechercheListee();
				if(m<listeAnnonces.size())
				{
					b3=listeAnnonces.get(m);
					TableTypesContrat ttc = (TableTypesContrat)  queryIDC.list().get(m);
					
					String NomTypeContrat = ttc.getNomtypecontrat();
					b3.setNomTypeContrat(NomTypeContrat);
					listeAnnonces.set(m, b3);
					
					
				}
								
			}
				
			
		}
		Query queryINM = sessions.createQuery("FROM TableClasse where IDCLASSE IN :inm ").setParameterList("inm", ArrayINM);
		
		if(!queryINM.list().isEmpty())
		{
			
			for(int n=0; n<queryINM.list().size(); n++)
			{
				RechercheListee b4 = new RechercheListee();
				if(n<listeAnnonces.size())
				{
					b4=listeAnnonces.get(n);
					TableClasse tcl = (TableClasse) queryINM.list().get(n);
					
					String NomClasse = tcl.getNomclasse();
					b4.setNomClasse(NomClasse);
					listeAnnonces.set(n, b4);
					
					
				}
								
			}
				
			
		}
		
		
		
		
		
		
		
					
		}
		
	
		
		return new ModelAndView("annonces","listeAnnonces",listeAnnonces);
	}
	
	public static BigDecimal convertIntToBD(int integer)
	 {
	  return  new BigDecimal( (double)integer );
	 }

}
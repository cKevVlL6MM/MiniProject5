package com.beans;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.model.*;

public class ProfileUtilisateur {
	
	private static final String roleEleve ="6mww5lakiz8w69yoswzb";
	private static final String roleEntreprise="ra2mqscru3i95k55cfne";
	private static final String roleAdmin="cgo9dbyvqmsrl8m72jvw";

	// par mesure de securite les info de login ne sont pas stock�es dans une instance de cette classe
	private TableUtilisateurs userInfo;
	private TableEleve EleveInfo = null;
	private TableEntreprises EntrepriseInfo = null;
	private TableAdmin AdminInfo = null;
	private ArrayList<BigDecimal> IdAnnoncesArchivees = null;
	
	private String roleUID ;
	
	
	
	
	private ProfileUtilisateur()
	{
		
	}
	
	public boolean isAdmin()
	{
		if(roleUID!= null)
	{
		
		if(this.roleUID==roleAdmin)
		{
			return true;
		}
	}
		return false;
	}
	
	public boolean isEleve()
	{
		if(roleUID!= null)
	{
		if(this.roleUID.equals(roleEleve))
		{
			return true;
		}
	}
		return false;
	}
	
	public boolean isEnterprise()
	{
		if(roleUID!= null)
		{
		if(this.roleUID.equals(roleEntreprise))
		{
			return true;
		}
		}
		return false;
	}
	
	
	
	public static TableUtilisateurs getTableUtilisateur(BigDecimal id)
	{
		SessionFactory sf =  HibernateUtil.getSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		
		Query query=sessions.createQuery("FROM TableUtilisateurs WHERE IDUSER = :id ")
				.setParameter("id", id);
		
		if(!query.list().isEmpty())
		{
			TableUtilisateurs tu = (TableUtilisateurs) query.list().get(0);
			return tu;
		}
		return null;
		
	}
	
	
	public ProfileUtilisateur(TableUtilisateurs tu, String role)
	{
		this.roleUID=role;
		this.userInfo = tu;
		
		SessionFactory sf =  HibernateUtil.getSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		
		if(role == roleEleve)
		{
			
			Query query=sessions.createQuery("FROM TableEleve WHERE IDUSER = :id ")
					.setParameter("id", tu.getIduser());
					
			if(!query.list().isEmpty())
			{
				TableEleve tempEleve = (TableEleve) query.list().get(0);
				this.EleveInfo=tempEleve;
			}
			
			Query query2=sessions.createQuery("FROM TableOffresArchivees WHERE IDUSER = :id ")
					.setParameter("id", tu.getIduser());
			
			//listeOffres= fill  (query.<TableOffres>list());	
			
			//si l'eleve a archivé des annonces
			if(!query2.list().isEmpty())
			{
				ArrayList<BigDecimal> tempList = new ArrayList<BigDecimal>();
				for(int i=0; i<query2.list().size();i++)
				{
					TableOffresArchivees tempOffreArchivee = (TableOffresArchivees) query2.list().get(i);
					tempList.add(tempOffreArchivee.getIdoffre());
					
					
				}
				this.setIdAnnoncesArchivees(tempList);
				
				
			}
			
			
			
			
			
		}
		else if(role.equals(roleEntreprise))
		{
			Query query=sessions.createQuery("FROM TableEntreprises WHERE IDUSER = :id ")
					.setParameter("id", tu.getIduser());
			
			if(!query.list().isEmpty())
			{
				TableEntreprises tempEntreprise = (TableEntreprises) query.list().get(0);
				this.EntrepriseInfo = tempEntreprise;
			}
			
			
			Query query3=sessions.createQuery("FROM TableOffres WHERE IDENTREPRISE = :id ")
					.setParameter("id", this.EntrepriseInfo.getIdentreprise());
			
			//listeOffres= fill  (query.<TableOffres>list());	
			
			//si l'eleve a archivé des annonces
			if(!query3.list().isEmpty())
			{
				ArrayList<BigDecimal> tempList2 = new ArrayList<BigDecimal>();
				for(int j=0; j<query3.list().size();j++)
				{
					TableOffres tempOffreArchivee2 = (TableOffres) query3.list().get(j);
					tempList2.add(tempOffreArchivee2.getIdoffre());
					
					
				}
				this.setIdAnnoncesArchivees(tempList2);
				
				
			}
			
			
			
			
			
			
			
			
		}
		else if(role == roleAdmin)
		{
			
			Query query=sessions.createQuery("FROM TableAdmin WHERE IDUSER = :id ")
					.setParameter("id", tu.getIduser());
			
			
			if(!query.list().isEmpty())
			{
				TableAdmin tempAdmin = (TableAdmin) query.list().get(0);
				this.AdminInfo=tempAdmin;
			}
			
			
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	public BigDecimal getId()
	{
		return this.userInfo.getIduser();
	}
	
	public BigDecimal CheckIDEntreprise()
	{
		if(this.EntrepriseInfo==null)
		{
			return null;
		}
		else
		{
			return this.EntrepriseInfo.getIdentreprise();
		}
	}
	
	public BigDecimal CheckIDEleve()
	{
		if(this.EleveInfo==null)
		{
			return null;
		}
		else
		{
			return this.EleveInfo.getIdeleve();
		}
	}
	public BigDecimal CheckIDAdmin()
	{
		if(this.AdminInfo==null)
		{
			return null;
		}
		else
		{
			return this.AdminInfo.getIdadmin();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public TableUtilisateurs getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(TableUtilisateurs userInfo) {
		this.userInfo = userInfo;
	}
	public TableEleve getEleveInfo() {
		return EleveInfo;
	}
	public void setEleveInfo(TableEleve eleveInfo) {
		EleveInfo = eleveInfo;
	}
	public TableEntreprises getEnterpriseInfo() {
		return EntrepriseInfo;
	}
	public void setEnterpriseInfo(TableEntreprises enterpriseInfo) {
		EntrepriseInfo = enterpriseInfo;
	}
	public TableAdmin getAdminInfo() {
		return AdminInfo;
	}
	public void setAdminInfo(TableAdmin adminInfo) {
		AdminInfo = adminInfo;
	}
	public ArrayList<BigDecimal> getIdAnnoncesArchivees() {
		return IdAnnoncesArchivees;
	}
	public void setIdAnnoncesArchivees(ArrayList<BigDecimal> annoncesArchivees) {
		IdAnnoncesArchivees = annoncesArchivees;
	}
	public String getRoleUID() {
		return roleUID;
	}
	public void setRoleUID(String roleUID) {
		this.roleUID = roleUID;
	}
	
	
	
	
}

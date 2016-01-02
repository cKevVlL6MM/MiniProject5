package com.beans;

import hibernate.model.TableEntreprises;
import hibernate.model.TableOffres;

public class RechercheListee {
	
	private TableOffres offre;
	private TableEntreprises enterprise;
	
	
	private String nomSecteur;
	private String nomCompetence;
	private String nomTypeContrat;
	
	public TableEntreprises getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(TableEntreprises enterprise) {
		this.enterprise = enterprise;
	}
	
	public TableOffres getOffre() {
		return offre;
	}
	public void setOffre(TableOffres offre) {
		this.offre = offre;
	}
	
	public String getNomSecteur() {
		return nomSecteur;
	}
	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}
	public String getNomCompetence() {
		return nomCompetence;
	}
	public void setNomCompetence(String nomCompetence) {
		this.nomCompetence = nomCompetence;
	}
	public String getNomTypeContrat() {
		return nomTypeContrat;
	}
	public void setNomTypeContrat(String nomTypeContrat) {
		this.nomTypeContrat = nomTypeContrat;
	}
	
	public String Adresse()
	{
		return this.enterprise.getAdresse()+" "+this.enterprise.getCodepostal();
	}
	
	
	
	

}

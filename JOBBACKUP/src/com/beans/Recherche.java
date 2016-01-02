package com.beans;

import java.math.BigDecimal;
import java.util.Date;

public class Recherche {


	private int idtypecontrat;
	private int idtypesecteur;
	private int idtypecompetence;
	private int idniveauminimum;
	private String titreoffre;
	private Date datepublication;
	private Date dureeoffre;
	
	
	public int getIdtypecontrat() {
		return idtypecontrat;
	}
	
	public void setIdtypecontrat(int idtypecontrat) {
		this.idtypecontrat = idtypecontrat;
	}
	
	public int getIdtypesecteur() {
		return idtypesecteur;
	}
	
	public void setIdtypesecteur(int idtypesecteur) {
		this.idtypesecteur = idtypesecteur;
	}
	
	public int getIdtypecompetence() {
		return idtypecompetence;
	}
	
	public void setIdtypecompetence(int idtypecompetence) {
		this.idtypecompetence = idtypecompetence;
	}
	
	public int getIdniveauminimum() {
		return idniveauminimum;
	}
	
	public void setIdniveauminimum(int idniveauminimum) {
		this.idniveauminimum = idniveauminimum;
	}
	
	public String getTitreoffre() {
		return titreoffre;
	}
	
	public void setTitreoffre(String titreoffre) {
		this.titreoffre = titreoffre;
	}
	
	public Date getDatepublication() {
		return datepublication;
	}
	
	public void setDatepublication(Date datepublication) {
		this.datepublication = datepublication;
	}
	
	public Date getDureeoffre() {
		return dureeoffre;
	}
	
	public void setDureeoffre(Date dureeoffre) {
		this.dureeoffre = dureeoffre;
	}
	
	
}

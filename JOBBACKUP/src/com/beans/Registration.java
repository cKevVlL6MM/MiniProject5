package com.beans;

import java.math.BigDecimal;

public class Registration {
	private String username;
	private String password;
	private String nom;
	private String prenom;
	private BigDecimal age;
	private String telephone;
	private String classe;
	private String email;
	private String civilite;
	private String options;
	private String adresse;
	private String typeSecteur;
	private String codePostal;
	private String fax;

	
	public String getTypeSecteur() {
		return typeSecteur;
	}
	public void setTypeSecteur(String typeSecteur) {
		this.typeSecteur = typeSecteur;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String type) {
		this.options = type;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String identifiant) {
		this.username = identifiant;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public BigDecimal getAge() {
		return age;
	}
	public void setAge(BigDecimal age) {
		this.age = age;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

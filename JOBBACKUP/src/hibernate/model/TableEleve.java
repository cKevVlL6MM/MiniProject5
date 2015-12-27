package hibernate.model;
// Generated 20 d�c. 2015 18:41:22 by Hibernate Tools 4.0.0.Final

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicUpdate;


import org.hibernate.annotations.DynamicUpdate;



/**
 * TableEleve generated by hbm2java
 */

@DynamicUpdate
public class TableEleve implements java.io.Serializable {

	private BigDecimal ideleve;
	private BigDecimal iduser;
	private BigDecimal idclasse;
	private String civilite;
	private String nom;
	private String prenom;
	private BigDecimal age;
	private String telephone;
	private String email;

	public TableEleve() {
	}

	public TableEleve(BigDecimal ideleve, BigDecimal iduser, BigDecimal idclasse, String civilite, String nom,
			String prenom, String email) {
		this.ideleve = ideleve;
		this.iduser = iduser;
		this.idclasse = idclasse;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.setEmail(email);
	}

	public TableEleve(BigDecimal ideleve, BigDecimal iduser, BigDecimal idclasse, String civilite, String nom,
			String prenom, BigDecimal age, String telephone, String email) {
		this.ideleve = ideleve;
		this.iduser = iduser;
		this.idclasse = idclasse;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.telephone = telephone;
		this.setEmail(email);
	}

	public BigDecimal getIdeleve() {
		return this.ideleve;
	}

	public void setIdeleve(BigDecimal ideleve) {
		this.ideleve = ideleve;
	}

	public BigDecimal getIduser() {
		return this.iduser;
	}

	public void setIduser(BigDecimal iduser) {
		this.iduser = iduser;
	}

	public BigDecimal getIdclasse() {
		return this.idclasse;
	}

	public void setIdclasse(BigDecimal idclasse) {
		this.idclasse = idclasse;
	}

	public String getCivilite() {
		return this.civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public BigDecimal getAge() {
		return this.age;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

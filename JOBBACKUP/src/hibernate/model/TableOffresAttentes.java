package hibernate.model;
// Generated 5 janv. 2016 16:17:19 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.util.Date;

/**
 * TableOffresAttentes generated by hbm2java
 */
public class TableOffresAttentes extends TableOffres implements java.io.Serializable {

	private BigDecimal idoffre;
	private BigDecimal identreprise;
	private BigDecimal idtypecontrat;
	private BigDecimal idtypesecteur;
	private BigDecimal idtypecompetence;
	private BigDecimal idniveauminimum;
	private String titreoffre;
	private String contenu;
	private Date datepublication;
	private Date dureeoffre;
	private Date datedebut;

	public TableOffresAttentes() {
	}

	public TableOffresAttentes(BigDecimal idoffre, BigDecimal identreprise, BigDecimal idtypecontrat,
			BigDecimal idtypesecteur, BigDecimal idtypecompetence, BigDecimal idniveauminimum, String titreoffre,
			String contenu, Date datepublication) {
		this.idoffre = idoffre;
		this.identreprise = identreprise;
		this.idtypecontrat = idtypecontrat;
		this.idtypesecteur = idtypesecteur;
		this.idtypecompetence = idtypecompetence;
		this.idniveauminimum = idniveauminimum;
		this.titreoffre = titreoffre;
		this.contenu = contenu;
		this.datepublication = datepublication;
	}

	public TableOffresAttentes(BigDecimal idoffre, BigDecimal identreprise, BigDecimal idtypecontrat,
			BigDecimal idtypesecteur, BigDecimal idtypecompetence, BigDecimal idniveauminimum, String titreoffre,
			String contenu, Date datepublication, Date dureeoffre, Date datedebut) {
		this.idoffre = idoffre;
		this.identreprise = identreprise;
		this.idtypecontrat = idtypecontrat;
		this.idtypesecteur = idtypesecteur;
		this.idtypecompetence = idtypecompetence;
		this.idniveauminimum = idniveauminimum;
		this.titreoffre = titreoffre;
		this.contenu = contenu;
		this.datepublication = datepublication;
		this.dureeoffre = dureeoffre;
		this.datedebut = datedebut;
	}

	public BigDecimal getIdoffre() {
		return this.idoffre;
	}

	public void setIdoffre(BigDecimal idoffre) {
		this.idoffre = idoffre;
	}

	public BigDecimal getIdentreprise() {
		return this.identreprise;
	}

	public void setIdentreprise(BigDecimal identreprise) {
		this.identreprise = identreprise;
	}

	public BigDecimal getIdtypecontrat() {
		return this.idtypecontrat;
	}

	public void setIdtypecontrat(BigDecimal idtypecontrat) {
		this.idtypecontrat = idtypecontrat;
	}

	public BigDecimal getIdtypesecteur() {
		return this.idtypesecteur;
	}

	public void setIdtypesecteur(BigDecimal idtypesecteur) {
		this.idtypesecteur = idtypesecteur;
	}

	public BigDecimal getIdtypecompetence() {
		return this.idtypecompetence;
	}

	public void setIdtypecompetence(BigDecimal idtypecompetence) {
		this.idtypecompetence = idtypecompetence;
	}

	public BigDecimal getIdniveauminimum() {
		return this.idniveauminimum;
	}

	public void setIdniveauminimum(BigDecimal idniveauminimum) {
		this.idniveauminimum = idniveauminimum;
	}

	public String getTitreoffre() {
		return this.titreoffre;
	}

	public void setTitreoffre(String titreoffre) {
		this.titreoffre = titreoffre;
	}

	public String getContenu() {
		return this.contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDatepublication() {
		return this.datepublication;
	}

	public void setDatepublication(Date datepublication) {
		this.datepublication = datepublication;
	}

	public Date getDureeoffre() {
		return this.dureeoffre;
	}

	public void setDureeoffre(Date dureeoffre) {
		this.dureeoffre = dureeoffre;
	}

	public Date getDatedebut() {
		return this.datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

}

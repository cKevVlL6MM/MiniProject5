package hibernate.model;
// Generated 20 d�c. 2015 18:41:22 by Hibernate Tools 4.0.0.Final

import java.math.BigDecimal;

/**
 * TableEntreprises generated by hbm2java
 */
import org.hibernate.annotations.DynamicUpdate;
@DynamicUpdate
public class TableEntreprises implements java.io.Serializable {

	private BigDecimal identreprise;
	private BigDecimal iduser;
	private BigDecimal idtypesecteur;
	private String nomsociete;
	private String adresse;
	private String codepostal;
	private String fax;
	private String email;
	private String telephone;

	public TableEntreprises() {
	}

	public TableEntreprises(BigDecimal identreprise, BigDecimal iduser, String nomsociete, String adresse,
			String codepostal, String email) {
		this.identreprise = identreprise;
		this.iduser = iduser;
		this.nomsociete = nomsociete;
		this.adresse = adresse;
		this.codepostal = codepostal;
		this.email = email;
	}

	public TableEntreprises(BigDecimal identreprise, BigDecimal iduser, BigDecimal idtypesecteur, String nomsociete,
			String adresse, String codepostal, String fax, String email, String telephone) {
		this.identreprise = identreprise;
		this.iduser = iduser;
		this.idtypesecteur = idtypesecteur;
		this.nomsociete = nomsociete;
		this.adresse = adresse;
		this.codepostal = codepostal;
		this.fax = fax;
		this.email = email;
		this.telephone = telephone;
	}

	public BigDecimal getIdentreprise() {
		return this.identreprise;
	}

	public void setIdentreprise(BigDecimal identreprise) {
		this.identreprise = identreprise;
	}

	public BigDecimal getIduser() {
		return this.iduser;
	}

	public void setIduser(BigDecimal iduser) {
		this.iduser = iduser;
	}

	public BigDecimal getIdtypesecteur() {
		return this.idtypesecteur;
	}

	public void setIdtypesecteur(BigDecimal idtypesecteur) {
		this.idtypesecteur = idtypesecteur;
	}

	public String getNomsociete() {
		return this.nomsociete;
	}

	public void setNomsociete(String nomsociete) {
		this.nomsociete = nomsociete;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodepostal() {
		return this.codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}

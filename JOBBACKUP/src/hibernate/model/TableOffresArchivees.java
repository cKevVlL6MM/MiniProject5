package hibernate.model;
// Generated 3 janv. 2016 18:02:03 by Hibernate Tools 4.0.0.Final

import java.math.BigDecimal;

/**
 * TableOffresArchivees generated by hbm2java
 */
public class TableOffresArchivees implements java.io.Serializable {

	private BigDecimal idoffrearchivee;
	private BigDecimal iduser;
	private BigDecimal idoffre;

	public TableOffresArchivees() {
	}

	public TableOffresArchivees(BigDecimal idoffrearchivee, BigDecimal iduser, BigDecimal idoffre) {
		this.idoffrearchivee = idoffrearchivee;
		this.iduser = iduser;
		this.idoffre = idoffre;
	}

	public BigDecimal getIdoffrearchivee() {
		return this.idoffrearchivee;
	}

	public void setIdoffrearchivee(BigDecimal idoffrearchivee) {
		this.idoffrearchivee = idoffrearchivee;
	}

	public BigDecimal getIduser() {
		return this.iduser;
	}

	public void setIduser(BigDecimal iduser) {
		this.iduser = iduser;
	}

	public BigDecimal getIdoffre() {
		return this.idoffre;
	}

	public void setIdoffre(BigDecimal idoffre) {
		this.idoffre = idoffre;
	}

}

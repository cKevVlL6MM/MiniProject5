package hibernate.model;
// Generated 20 dc. 2015 18:41:22 by Hibernate Tools 4.0.0.Final

import java.math.BigDecimal;

/**
 * TableCompte generated by hbm2java
 */

import org.hibernate.annotations.DynamicUpdate;
@DynamicUpdate
public class TableCompte implements java.io.Serializable {

	private BigDecimal idtypecompte;
	private String nomtypecompte;

	public TableCompte() {
	}

	public TableCompte(BigDecimal idtypecompte) {
		this.idtypecompte = idtypecompte;
	}

	public TableCompte(BigDecimal idtypecompte, String nomtypecompte) {
		this.idtypecompte = idtypecompte;
		this.nomtypecompte = nomtypecompte;
	}

	public BigDecimal getIdtypecompte() {
		return this.idtypecompte;
	}

	public void setIdtypecompte(BigDecimal idtypecompte) {
		this.idtypecompte = idtypecompte;
	}

	public String getNomtypecompte() {
		return this.nomtypecompte;
	}

	public void setNomtypecompte(String nomtypecompte) {
		this.nomtypecompte = nomtypecompte;
	}

}

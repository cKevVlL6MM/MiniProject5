package hibernate.model;
// Generated 20 d�c. 2015 18:41:22 by Hibernate Tools 4.0.0.Final

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicUpdate;



/**
 * TableClasse generated by hbm2java
 */
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
public class TableClasse implements java.io.Serializable {

	private BigDecimal idclasse;
	private String nomclasse;

	public TableClasse() {
	}

	public TableClasse(BigDecimal idclasse, String nomclasse) {
		this.idclasse = idclasse;
		this.nomclasse = nomclasse;
	}

	public BigDecimal getIdclasse() {
		return this.idclasse;
	}

	public void setIdclasse(BigDecimal idclasse) {
		this.idclasse = idclasse;
	}

	public String getNomclasse() {
		return this.nomclasse;
	}

	public void setNomclasse(String nomclasse) {
		this.nomclasse = nomclasse;
	}

}

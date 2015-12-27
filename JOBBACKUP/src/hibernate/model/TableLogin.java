package hibernate.model;
// Generated 20 d�c. 2015 18:41:22 by Hibernate Tools 4.0.0.Final

import java.math.BigDecimal;

import org.hibernate.annotations.DynamicUpdate;

/**
 * TableLogin generated by hbm2java
 */
import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
public class TableLogin implements java.io.Serializable {

	private BigDecimal idlogin;
	private BigDecimal iduser;
	private String identifiant;
	private String password;

	public TableLogin() {
	}

	public TableLogin(BigDecimal idlogin, BigDecimal iduser, String identifiant, String password) {
		this.idlogin = idlogin;
		this.iduser = iduser;
		this.identifiant = identifiant;
		this.password = password;
	}

	public BigDecimal getIdlogin() {
		return this.idlogin;
	}

	public void setIdlogin(BigDecimal idlogin) {
		this.idlogin = idlogin;
	}

	public BigDecimal getIduser() {
		return this.iduser;
	}

	public void setIduser(BigDecimal iduser) {
		this.iduser = iduser;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

package hibernate.model;
// Generated 20 d�c. 2015 18:41:22 by Hibernate Tools 4.0.0.Final

import java.math.BigDecimal;
import java.util.Date;

/**
 * TableStatistique generated by hbm2java
 */
import org.hibernate.annotations.DynamicUpdate;
@DynamicUpdate
public class TableStatistique implements java.io.Serializable {

	private BigDecimal idlignestat;
	private Date datestat;
	private BigDecimal nombreinscrits;
	private BigDecimal nombreentreprise;
	private BigDecimal nombreeleves;
	private BigDecimal nombreoffres;

	public TableStatistique() {
	}

	public TableStatistique(BigDecimal idlignestat, Date datestat, BigDecimal nombreinscrits,
			BigDecimal nombreentreprise, BigDecimal nombreeleves, BigDecimal nombreoffres) {
		this.idlignestat = idlignestat;
		this.datestat = datestat;
		this.nombreinscrits = nombreinscrits;
		this.nombreentreprise = nombreentreprise;
		this.nombreeleves = nombreeleves;
		this.nombreoffres = nombreoffres;
	}

	public BigDecimal getIdlignestat() {
		return this.idlignestat;
	}

	public void setIdlignestat(BigDecimal idlignestat) {
		this.idlignestat = idlignestat;
	}

	public Date getDatestat() {
		return this.datestat;
	}

	public void setDatestat(Date datestat) {
		this.datestat = datestat;
	}

	public BigDecimal getNombreinscrits() {
		return this.nombreinscrits;
	}

	public void setNombreinscrits(BigDecimal nombreinscrits) {
		this.nombreinscrits = nombreinscrits;
	}

	public BigDecimal getNombreentreprise() {
		return this.nombreentreprise;
	}

	public void setNombreentreprise(BigDecimal nombreentreprise) {
		this.nombreentreprise = nombreentreprise;
	}

	public BigDecimal getNombreeleves() {
		return this.nombreeleves;
	}

	public void setNombreeleves(BigDecimal nombreeleves) {
		this.nombreeleves = nombreeleves;
	}

	public BigDecimal getNombreoffres() {
		return this.nombreoffres;
	}

	public void setNombreoffres(BigDecimal nombreoffres) {
		this.nombreoffres = nombreoffres;
	}

}

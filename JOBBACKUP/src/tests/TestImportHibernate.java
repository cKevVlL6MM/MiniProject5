package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import hibernate.model.TableEleve;

public class TestImportHibernate {

	@Test
	public void testTableEleveBigDecimalBigDecimalBigDecimalStringStringStringString() {
		double ic = 1;
		double ie = 2;
		double iu = 3;
		double age = 21;
		String civ = "Mr";
		String eMail = "test@test";
		String nom = "pnom";
		String prenom = "pprenom";
		String telephone = "012024342";
		
		BigDecimal bic = BigDecimal.valueOf(ic);
		BigDecimal bie = BigDecimal.valueOf(ie);
		BigDecimal biu = BigDecimal.valueOf(iu);
		BigDecimal bage = BigDecimal.valueOf(age);

		/*public TableEleve(BigDecimal ideleve, BigDecimal iduser, BigDecimal idclasse, String civilite, String nom,
				String prenom, BigDecimal age, String telephone, String email) { */
		// on test juste que les classe AUTOMATIQUEMENT CREE  fonctionnent
		TableEleve e = new TableEleve(bie,biu,bic,civ,nom,prenom,bage,telephone,eMail);
		
		assertEquals(e.getCivilite(),civ);
		assertEquals(e.getAge(),bage);
		assertEquals(e.getEmail(),eMail);
		assertEquals(e.getNom(), nom);
		assertEquals(e.getPrenom(),prenom);
		assertEquals(e.getIdclasse(),bic);
		assertEquals(e.getIduser(),biu);
		
		
	}

	

}

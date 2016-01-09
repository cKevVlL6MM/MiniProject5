package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import com.beans.HibernateUtil;

import hibernate.model.TableUtilisateurs;

public class TestSQL {

	@Test
	public void testSelect() {
		//On test le routage automatique HBM et Model de Hibernate + le bon usage d'une requete SQL sous hibernate
		//Ce test nous a permis de remarquer que l'utilisation de clé primaire composite fonctionnait très mal sous hibernate
		
		double id = 1;
		BigDecimal Bid = BigDecimal.valueOf(id);
		//A tester si base non vide !
		// Ce test n'a de sens que si la base est non vide
		SessionFactory sf =  HibernateUtil.getSessionFactory();
		//SessionFactory sessionFactory = createSessionFactory();
		Session sessions= sf.openSession();
		TableUtilisateurs tu = null;
		Query query=sessions.createSQLQuery("Select * FROM Table_Utilisateurs")
				.addEntity(TableUtilisateurs.class)
				.setParameter("id", Bid);
		
		if(!query.list().isEmpty())
		{
			 tu = (TableUtilisateurs) query.list().get(0);
		
			assert(tu.getClass().isInstance(TableUtilisateurs.class));
		
			
		}
		
		
	}

}

package hibernate.dao;
import hibernate.model.*; 
// Generated 20 d�c. 2015 18:42:20 by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class TableTypesContrat.
 * @see hibernate.dao.TableTypesContrat
 * @author Hibernate Tools
 */
public class TableTypesContratHome {

	private static final Log log = LogFactory.getLog(TableTypesContratHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TableTypesContrat transientInstance) {
		log.debug("persisting TableTypesContrat instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TableTypesContrat instance) {
		log.debug("attaching dirty TableTypesContrat instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableTypesContrat instance) {
		log.debug("attaching clean TableTypesContrat instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TableTypesContrat persistentInstance) {
		log.debug("deleting TableTypesContrat instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableTypesContrat merge(TableTypesContrat detachedInstance) {
		log.debug("merging TableTypesContrat instance");
		try {
			TableTypesContrat result = (TableTypesContrat) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TableTypesContrat findById(java.math.BigDecimal id) {
		log.debug("getting TableTypesContrat instance with id: " + id);
		try {
			TableTypesContrat instance = (TableTypesContrat) sessionFactory.getCurrentSession()
					.get("hibernate.dao.TableTypesContrat", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TableTypesContrat instance) {
		log.debug("finding TableTypesContrat instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("hibernate.dao.TableTypesContrat")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

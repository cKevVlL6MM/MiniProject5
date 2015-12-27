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
 * Home object for domain model class TableOffres.
 * @see hibernate.dao.TableOffres
 * @author Hibernate Tools
 */
public class TableOffresHome {

	private static final Log log = LogFactory.getLog(TableOffresHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TableOffres transientInstance) {
		log.debug("persisting TableOffres instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TableOffres instance) {
		log.debug("attaching dirty TableOffres instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableOffres instance) {
		log.debug("attaching clean TableOffres instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TableOffres persistentInstance) {
		log.debug("deleting TableOffres instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableOffres merge(TableOffres detachedInstance) {
		log.debug("merging TableOffres instance");
		try {
			TableOffres result = (TableOffres) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TableOffres findById(java.math.BigDecimal id) {
		log.debug("getting TableOffres instance with id: " + id);
		try {
			TableOffres instance = (TableOffres) sessionFactory.getCurrentSession().get("hibernate.dao.TableOffres",
					id);
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

	public List findByExample(TableOffres instance) {
		log.debug("finding TableOffres instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("hibernate.dao.TableOffres")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

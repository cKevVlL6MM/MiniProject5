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
 * Home object for domain model class TableNewsletters.
 * @see hibernate.dao.TableNewsletters
 * @author Hibernate Tools
 */
public class TableNewslettersHome {

	private static final Log log = LogFactory.getLog(TableNewslettersHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TableNewsletters transientInstance) {
		log.debug("persisting TableNewsletters instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TableNewsletters instance) {
		log.debug("attaching dirty TableNewsletters instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableNewsletters instance) {
		log.debug("attaching clean TableNewsletters instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TableNewsletters persistentInstance) {
		log.debug("deleting TableNewsletters instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableNewsletters merge(TableNewsletters detachedInstance) {
		log.debug("merging TableNewsletters instance");
		try {
			TableNewsletters result = (TableNewsletters) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TableNewsletters findById(java.math.BigDecimal id) {
		log.debug("getting TableNewsletters instance with id: " + id);
		try {
			TableNewsletters instance = (TableNewsletters) sessionFactory.getCurrentSession()
					.get("hibernate.dao.TableNewsletters", id);
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

	public List findByExample(TableNewsletters instance) {
		log.debug("finding TableNewsletters instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("hibernate.dao.TableNewsletters")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

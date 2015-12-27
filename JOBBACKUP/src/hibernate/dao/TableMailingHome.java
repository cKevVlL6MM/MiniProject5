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
 * Home object for domain model class TableMailing.
 * @see hibernate.dao.TableMailing
 * @author Hibernate Tools
 */
public class TableMailingHome {

	private static final Log log = LogFactory.getLog(TableMailingHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TableMailing transientInstance) {
		log.debug("persisting TableMailing instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TableMailing instance) {
		log.debug("attaching dirty TableMailing instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableMailing instance) {
		log.debug("attaching clean TableMailing instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TableMailing persistentInstance) {
		log.debug("deleting TableMailing instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableMailing merge(TableMailing detachedInstance) {
		log.debug("merging TableMailing instance");
		try {
			TableMailing result = (TableMailing) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TableMailing findById(java.math.BigDecimal id) {
		log.debug("getting TableMailing instance with id: " + id);
		try {
			TableMailing instance = (TableMailing) sessionFactory.getCurrentSession().get("hibernate.dao.TableMailing",
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

	public List findByExample(TableMailing instance) {
		log.debug("finding TableMailing instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("hibernate.dao.TableMailing")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

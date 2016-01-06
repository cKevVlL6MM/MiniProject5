package hibernate.dao;
// Generated 6 janv. 2016 19:29:58 by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import hibernate.model.TableDocuments;

/**
 * Home object for domain model class TableDocuments.
 * @see hibernate.model.TableDocuments
 * @author Hibernate Tools
 */
public class TableDocumentsHome {

	private static final Log log = LogFactory.getLog(TableDocumentsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TableDocuments transientInstance) {
		log.debug("persisting TableDocuments instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TableDocuments instance) {
		log.debug("attaching dirty TableDocuments instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableDocuments instance) {
		log.debug("attaching clean TableDocuments instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TableDocuments persistentInstance) {
		log.debug("deleting TableDocuments instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableDocuments merge(TableDocuments detachedInstance) {
		log.debug("merging TableDocuments instance");
		try {
			TableDocuments result = (TableDocuments) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TableDocuments findById(java.math.BigDecimal id) {
		log.debug("getting TableDocuments instance with id: " + id);
		try {
			TableDocuments instance = (TableDocuments) sessionFactory.getCurrentSession()
					.get("hibernate.model.TableDocuments", id);
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

	public List findByExample(TableDocuments instance) {
		log.debug("finding TableDocuments instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("hibernate.model.TableDocuments")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

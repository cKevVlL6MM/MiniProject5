package hibernate.dao;
// Generated 5 janv. 2016 11:25:45 by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import hibernate.model.TableNews;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TableNews.
 * @see hibernate.TableNews
 * @author Hibernate Tools
 */
public class TableNewsHome {

	private static final Log log = LogFactory.getLog(TableNewsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TableNews transientInstance) {
		log.debug("persisting TableNews instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TableNews instance) {
		log.debug("attaching dirty TableNews instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableNews instance) {
		log.debug("attaching clean TableNews instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TableNews persistentInstance) {
		log.debug("deleting TableNews instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableNews merge(TableNews detachedInstance) {
		log.debug("merging TableNews instance");
		try {
			TableNews result = (TableNews) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TableNews findById(java.math.BigDecimal id) {
		log.debug("getting TableNews instance with id: " + id);
		try {
			TableNews instance = (TableNews) sessionFactory.getCurrentSession().get("hibernate.TableNews", id);
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

	public List<TableNews> findByExample(TableNews instance) {
		log.debug("finding TableNews instance by example");
		try {
			List<TableNews> results = (List<TableNews>) sessionFactory.getCurrentSession()
					.createCriteria("hibernate.TableNews").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

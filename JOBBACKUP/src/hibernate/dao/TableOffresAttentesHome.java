package hibernate.dao;
// Generated 5 janv. 2016 16:17:19 by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import hibernate.model.TableOffresAttentes;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TableOffresAttentes.
 * @see hibernate.TableOffresAttentes
 * @author Hibernate Tools
 */
public class TableOffresAttentesHome {

	private static final Log log = LogFactory.getLog(TableOffresAttentesHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TableOffresAttentes transientInstance) {
		log.debug("persisting TableOffresAttentes instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TableOffresAttentes instance) {
		log.debug("attaching dirty TableOffresAttentes instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableOffresAttentes instance) {
		log.debug("attaching clean TableOffresAttentes instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TableOffresAttentes persistentInstance) {
		log.debug("deleting TableOffresAttentes instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableOffresAttentes merge(TableOffresAttentes detachedInstance) {
		log.debug("merging TableOffresAttentes instance");
		try {
			TableOffresAttentes result = (TableOffresAttentes) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TableOffresAttentes findById(java.math.BigDecimal id) {
		log.debug("getting TableOffresAttentes instance with id: " + id);
		try {
			TableOffresAttentes instance = (TableOffresAttentes) sessionFactory.getCurrentSession()
					.get("hibernate.TableOffresAttentes", id);
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

	public List<TableOffresAttentes> findByExample(TableOffresAttentes instance) {
		log.debug("finding TableOffresAttentes instance by example");
		try {
			List<TableOffresAttentes> results = (List<TableOffresAttentes>) sessionFactory.getCurrentSession()
					.createCriteria("hibernate.TableOffresAttentes").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

package hibernate.dao;
// Generated 5 janv. 2016 11:25:45 by Hibernate Tools 4.3.1.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import hibernate.model.TableEvenements;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TableEvenements.
 * @see hibernate.TableEvenements
 * @author Hibernate Tools
 */
public class TableEvenementsHome {

	private static final Log log = LogFactory.getLog(TableEvenementsHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(TableEvenements transientInstance) {
		log.debug("persisting TableEvenements instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TableEvenements instance) {
		log.debug("attaching dirty TableEvenements instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TableEvenements instance) {
		log.debug("attaching clean TableEvenements instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TableEvenements persistentInstance) {
		log.debug("deleting TableEvenements instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TableEvenements merge(TableEvenements detachedInstance) {
		log.debug("merging TableEvenements instance");
		try {
			TableEvenements result = (TableEvenements) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TableEvenements findById(java.math.BigDecimal id) {
		log.debug("getting TableEvenements instance with id: " + id);
		try {
			TableEvenements instance = (TableEvenements) sessionFactory.getCurrentSession()
					.get("hibernate.TableEvenements", id);
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

	public List<TableEvenements> findByExample(TableEvenements instance) {
		log.debug("finding TableEvenements instance by example");
		try {
			List<TableEvenements> results = (List<TableEvenements>) sessionFactory.getCurrentSession()
					.createCriteria("hibernate.TableEvenements").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}

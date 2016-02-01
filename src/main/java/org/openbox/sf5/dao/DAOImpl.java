package org.openbox.sf5.dao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.model.AbstractDbEntity;

// http://stackoverflow.com/questions/799889/hibernate-doesnt-save-and-doesnt-throw-exceptions

// http://docs.jboss.org/hibernate/orm/4.3/devguide/en-US/html_single/#d5e729
// 2.4.2. Session-per-request pattern  -
// Within this pattern there is a common technique of defining a current session to simplify the need of passing this Session around
// to all the application components that may need access to it. Hibernate provides support for this technique through the getCurrentSession
// method of the SessionFactory. The concept of a "current" session has to have a scope that defines the bounds in which the notion of "current" is valid.

@Named
// @ApplicationScoped
@Stateless // http://docs.oracle.com/javaee/6/tutorial/doc/gipjg.html
// @MessageDriven // https://docs.oracle.com/javaee/6/tutorial/doc/bnbpo.html
public class DAOImpl implements DAO, Serializable {

	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public <T extends AbstractDbEntity> void add(T obj) {

		Session s = cm.getSessionFactroy().openSession();
		// Session s = cm.getSessionFactroy().getCurrentSession();
		Transaction trans = null;
		try {

			trans = s.beginTransaction();
			s.save(obj);
			trans.commit();
		}

		catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			throw e;
		}

		finally {
			s.close();
		}

	}

	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public <T extends AbstractDbEntity> void remove(Class<T> type, long id) {

		Session s = cm.getSessionFactroy().openSession();
		// Session s = cm.getSessionFactroy().getCurrentSession();
		Transaction trans = null;
		try {

			trans = s.beginTransaction();
			@SuppressWarnings("unchecked")
			T c = (T) s.get(type, id);
			s.delete(c);
			trans.commit();
		}

		catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			throw e;
		}

		finally {
			s.close();
		}
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public <T extends AbstractDbEntity> void update(T obj) {

		Session s = cm.getSessionFactroy().openSession();
		// Session s = cm.getSessionFactroy().getCurrentSession();
		Transaction trans = null;
		try {

			trans = s.beginTransaction();
			s.update(obj);
			trans.commit();
		}

		catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			throw e;
		}

		finally {
			s.close();
		}

	}

	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public <T extends AbstractDbEntity> T select(Class<T> type, long id) {

		Session s = cm.getSessionFactroy().openSession();
		// Session s = cm.getSessionFactroy().getCurrentSession();
		Transaction trans = null;
		// T obj = null;

		try {
			trans = s.beginTransaction();

			@SuppressWarnings("unchecked")
			T obj = (T) s.get(type, id);
			return obj;

		} catch (Exception e) {
			if (trans != null) {
				// trans.rollback();
			}
			throw e;
		}

		finally {
			s.close();
		}

	}

	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public <T extends AbstractDbEntity> void saveOrUpdate(T obj) {

		Session s = cm.getSessionFactroy().openSession();
		// Session s = cm.getSessionFactroy().getCurrentSession();
		Transaction trans = null;
		try {

			trans = s.beginTransaction();
			s.saveOrUpdate(obj);
			trans.commit();
		}

		catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			throw e;
		}

		finally {
			s.close();
		}
	}

	// @Inject
	@EJB
	private ConnectionManager cm;

	@Override
	public ConnectionManager getCm() {
		return cm;
	}

	@Override
	public void setCm(ConnectionManager cm) {
		this.cm = cm;
	}

	private static final long serialVersionUID = 4283051495842205423L;
}

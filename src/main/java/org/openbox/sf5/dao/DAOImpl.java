package org.openbox.sf5.dao;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.model.AbstractDbEntity;

@Named
@ApplicationScoped
public class DAOImpl implements DAO, Serializable {

	@Override
	public <T extends AbstractDbEntity> void add(T obj) {
		Session s = cm.getSessionFactroy().openSession();

		s.beginTransaction();
		s.save(obj);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public <T extends AbstractDbEntity> void remove(Class<T> type, long id) {

		Session s = cm.getSessionFactroy().openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		T c = (T) s.get(type, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public <T extends AbstractDbEntity> void update(T obj) {

		Session s = cm.getSessionFactroy().openSession();
		s.beginTransaction();
		s.update(obj);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public <T extends AbstractDbEntity> T select(Class<T> type, long id) {

		Session s = cm.getSessionFactroy().openSession();
		s.beginTransaction();
		@SuppressWarnings("unchecked")
		T obj = (T) s.get(type, id);
		s.close();
		return obj;
	}

	@Override
	public <T extends AbstractDbEntity> void saveOrUpdate(T obj) {

		Session s = cm.getSessionFactroy().openSession();
		s.beginTransaction();
		s.saveOrUpdate(obj);
		s.getTransaction().commit();
		s.close();
	}

	@Inject
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

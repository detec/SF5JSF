package org.openbox.sf5.dao;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.openbox.sf5.db.ConnectionManager;

@Named
@SessionScoped
public class DAOImpl implements DAO, Serializable {

	private static final long serialVersionUID = 4283051495842205423L;

	@Override
	public void add(Object obj) {
		// Session s = HibernateUtil.openSession();

		Session s = cm.getSessionFactroy().openSession();

		s.beginTransaction();
		s.save(obj);
		s.getTransaction().commit();
		s.close();

	}

	@Override
	public void remove(Class<?> clazz, long id) {
		// Session s = HibernateUtil.openSession();

		Session s = cm.getSessionFactroy().openSession();
		s.beginTransaction();
		Object c = s.get(clazz, id);
		s.delete(c);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public void update(Object obj) {
		// Session s = HibernateUtil.openSession();

		Session s = cm.getSessionFactroy().openSession();
		s.beginTransaction();
		s.update(obj);
		s.getTransaction().commit();
		s.close();
	}

	@Override
	public Object select(Class<?> clazz, long id) {
		// Session s = HibernateUtil.openSession();

		Session s = cm.getSessionFactroy().openSession();
		s.beginTransaction();
		Object obj = s.get(clazz, id);
		s.close();
		return obj;
	}

	@Override
	public void saveOrUpdate(Object obj) {
		// Session s = HibernateUtil.openSession();

		Session s = cm.getSessionFactroy().openSession();
		s.beginTransaction();
		s.saveOrUpdate(obj);
		s.getTransaction().commit();
		s.close();
	}

	@Inject
	private ConnectionManager cm;
}

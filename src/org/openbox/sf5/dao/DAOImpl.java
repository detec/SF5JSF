package org.openbox.sf5.dao;


import org.hibernate.Session;
import org.openbox.sf5.db.HibernateUtil;


public class DAOImpl implements DAO {

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.save(obj);
        s.getTransaction().commit();
        s.close();

	}

	@Override
	public void remove(Class<?> clazz, long id) {
		// TODO Auto-generated method stub
        
		Session s=HibernateUtil.openSession();
        s.beginTransaction();
        Object c=(Object)s.get(clazz , id);
        s.delete(c);
        s.getTransaction().commit();
        s.close();  
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.update(obj);
        s.getTransaction().commit();
        s.close();
	}
	
	
	@Override
	public Object select(Class<?> clazz, long id) {
		Session s=HibernateUtil.openSession();
		  s.beginTransaction();
		  Object obj = (Object) s.get(clazz, id);
		  s.close();
		  return obj;
	}
	
	@Override
	public void saveOrUpdate(Object obj) {
		Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.saveOrUpdate(obj);
        s.getTransaction().commit();
        s.close();
	}
}

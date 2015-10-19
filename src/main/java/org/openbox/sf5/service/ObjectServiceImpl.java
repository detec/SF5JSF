package org.openbox.sf5.service;

import org.openbox.sf5.dao.DAO;
import org.openbox.sf5.dao.DAOImpl;

public class ObjectServiceImpl implements ObjectService {

	private DAO DAO = new DAOImpl();

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		DAO.add(obj);
	}

	@Override
	public void remove(Class<?> clazz, long id) {
		// TODO Auto-generated method stub
		DAO.remove(clazz, id);
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		DAO.update(obj);
	}

	@Override
	public Object select(Class<?> clazz, long id) {
		return DAO.select(clazz, id);
	}

	@Override
	public void saveOrUpdate(Object obj) {
		DAO.saveOrUpdate(obj);
	}

}

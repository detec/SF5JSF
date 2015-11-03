package org.openbox.sf5.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.openbox.sf5.dao.DAO;

@Named
@SessionScoped
public class ObjectServiceImpl implements ObjectService, Serializable {

	private static final long serialVersionUID = 4462873069745434522L;
	//private DAO DAO = new DAOImpl();

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

	@Inject
	private DAO DAO;

}

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

	@Override
	public <T> void add(T obj) {
		DAO.add(obj);
	}

	@Override
	public <T> void remove(Class<T> type, long id) {
		DAO.remove(type, id);
	}


	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		DAO.update(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T select(Class<T> type, long id) {
		return (T) DAO.select(type, id);
	}

	@Override
	public <T> void saveOrUpdate(T obj) {
		DAO.saveOrUpdate(obj);
	}

	@Inject
	private DAO DAO;

	@Override
	public DAO getDAO() {
		return DAO;
	}

	@Override
	public void setDAO(DAO dAO) {
		DAO = dAO;
	}


}

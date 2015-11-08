package org.openbox.sf5.service;

import org.openbox.sf5.dao.DAO;

//import java.util.List;

public interface ObjectService {

	public void add(Object obj);

	//public List<Object> list(String className);

	public void remove(Class<?> clazz, long id);

	public void update(Object obj);

	public Object select(Class<?> clazz, long id);

	public void saveOrUpdate(Object obj);

	public DAO getDAO();

	public void setDAO(DAO dAO);
}

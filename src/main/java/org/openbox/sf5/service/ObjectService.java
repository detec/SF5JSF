package org.openbox.sf5.service;

import org.openbox.sf5.dao.DAO;


public interface ObjectService {

	public <T> void add(T obj);

	public <T> void remove(Class<T> type, long id);

	public <T> void update(T obj);

	public <T> T select(Class<T> type, long id);

	public <T> void saveOrUpdate(T obj);

	public DAO getDAO();

	public void setDAO(DAO dAO);
}

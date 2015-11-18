package org.openbox.sf5.dao;

import org.openbox.sf5.db.ConnectionManager;

public interface DAO {

	public <T> void add(T obj);

	public <T> void remove(Class<T> type, long id);

	public <T> void update(T obj);

	public <T> Object select(Class<T> type, long id);

	public <T> void saveOrUpdate(T obj);

	public ConnectionManager getCm();

	public void setCm(ConnectionManager cm);

}

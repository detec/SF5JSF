package org.openbox.sf5.dao;

import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.model.AbstractDbEntity;

public interface DAO {

	public <T extends AbstractDbEntity> void add(T obj);

	public <T extends AbstractDbEntity> void remove(Class<T> type, long id);

	public <T extends AbstractDbEntity> void update(T obj);

	public <T extends AbstractDbEntity> T select(Class<T> type, long id);

	public <T extends AbstractDbEntity> void saveOrUpdate(T obj);

	public ConnectionManager getCm();

	public void setCm(ConnectionManager cm);

}

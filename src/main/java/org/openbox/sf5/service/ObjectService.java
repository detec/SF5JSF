package org.openbox.sf5.service;

import org.openbox.sf5.dao.DAO;
import org.openbox.sf5.model.AbstractDbEntity;

public interface ObjectService {

	public <T extends AbstractDbEntity> void add(T obj);

	public <T extends AbstractDbEntity> void remove(Class<T> type, long id);

	public <T extends AbstractDbEntity> void update(T obj);

	public <T extends AbstractDbEntity> T select(Class<T> type, long id);

	public <T extends AbstractDbEntity> void saveOrUpdate(T obj);

	public DAO getDAO();

	public void setDAO(DAO dAO);
}

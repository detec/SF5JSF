package org.openbox.sf5.service;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.openbox.sf5.dao.DAO;
import org.openbox.sf5.model.AbstractDbEntity;

@Named
@ApplicationScoped
public class ObjectServiceImpl implements ObjectService, Serializable {

	private static final long serialVersionUID = 4462873069745434522L;

	@Override
	public <T extends AbstractDbEntity> void add(T obj) {
		DAO.add(obj);
	}

	@Override
	public <T extends AbstractDbEntity> void remove(Class<T> type, long id) {
		DAO.remove(type, id);
	}

	@Override
	public <T extends AbstractDbEntity> void update(T obj) {
		DAO.update(obj);
	}

	@Override
	public <T extends AbstractDbEntity> T select(Class<T> type, long id) {
		return DAO.select(type, id);
	}

	@Override
	public <T extends AbstractDbEntity> void saveOrUpdate(T obj) {
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

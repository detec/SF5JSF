package org.openbox.sf5.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Criterion;
import org.openbox.sf5.dao.DAOList;

@Named("ObjectsListService")
@ApplicationScoped
public class ObjectsListService implements Serializable {

	public <T> List<T> ObjectsList(Class<T> type) {
		return dao.list(type);
	}

	public <T> List<T> ObjectsCriterionList(Class<T> type, Criterion criterion) {
		return dao.restrictionList(type, criterion);
	}

	@Inject
	private DAOList dao;

	public DAOList getDao() {
		return dao;
	}

	public void setDao(DAOList dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -3631362805987631432L;
}

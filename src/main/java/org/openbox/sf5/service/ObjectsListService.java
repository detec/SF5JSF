package org.openbox.sf5.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Criterion;
import org.openbox.sf5.dao.DAOList;

@Named("ObjectsListService")
@SessionScoped
public class ObjectsListService implements Serializable {

	public DAOList getDao() {
		return dao;
	}

	public void setDao(DAOList dao) {
		this.dao = dao;
	}

	private static final long serialVersionUID = -3631362805987631432L;

	public List<?> ObjectsList(Class<?> clazz) {

		return dao.list(clazz);

	}

	public List<?> ObjectsCriterionList(Class<?> clazz, Criterion criterion) {
		return dao.restrictionList(clazz, criterion);
	}

	@Inject
	private DAOList dao;
}

package org.openbox.sf5.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;


public interface DAOList {

	public List<?> list(Class<?> clazz);

	public List<?> restrictionList(Class<?> clazz, Criterion criterion);
}

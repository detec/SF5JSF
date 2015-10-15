package org.openbox.sf5.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public interface DAOList {

	public List<?> list(Class<?> clazz);
	
	public List<?> restrictionList(Class<?> clazz, Criterion criterion);
}

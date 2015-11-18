package org.openbox.sf5.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.openbox.sf5.db.ConnectionManager;

public interface DAOList {

	public <T> List<T> list(Class<T> type);

	public <T> List<T> restrictionList(Class<T> type, Criterion criterion);

	public ConnectionManager getCm();

	public void setCm(ConnectionManager cm);
}

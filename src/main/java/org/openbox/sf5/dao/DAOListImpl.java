package org.openbox.sf5.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.openbox.sf5.db.ConnectionManager;

@Named
@SessionScoped
public class DAOListImpl implements DAOList, Serializable {

	private static final long serialVersionUID = -4127115450739908412L;

	@Override
	public List<?> list(Class<?> clazz) {

		List<?> list = new ArrayList<>();
		// Session s = HibernateUtil.openSession();

		Session s = cm.getSessionFactroy().openSession();

		s.beginTransaction();
		list = s.createQuery("from " + clazz.getName()).list();
		s.getTransaction().commit();
		s.close();
		return list;
	}

	@Override
	public ConnectionManager getCm() {
		return cm;
	}

	@Override
	public void setCm(ConnectionManager cm) {
		this.cm = cm;
	}

	@Override
	public List<?> restrictionList(Class<?> clazz, Criterion criterion) {
		// Session s = HibernateUtil.openSession();

		Session session = cm.getSessionFactroy().openSession();
		Criteria criteria = session.createCriteria(clazz).add(criterion);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY); // kill
																					// duplicates
		return criteria.list();
	}

	@Inject
	private ConnectionManager cm;

}

package org.openbox.sf5.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.db.HibernateUtil;
import org.openbox.sf5.db.Transponders;

public class DAOListImpl implements DAOList {


	@Override
	public List<?> list(Class<?> clazz) {
	
		List<?> list = new ArrayList<>();
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        list = (List<?>) s.createQuery("from " + clazz.getName()).list();
        s.getTransaction().commit();
        s.close();
        return list;
	}

	@Override
	public List<?> restrictionList(Class<?> clazz, Criterion criterion) {
		Session session = HibernateUtil.openSession();
		Criteria criteria = session.createCriteria(clazz).add(criterion);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); // kill duplicates
		return criteria.list();
	}



}

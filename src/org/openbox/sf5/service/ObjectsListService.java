package org.openbox.sf5.service;


import java.util.List;

import org.hibernate.criterion.Criterion;
import org.openbox.sf5.dao.DAOListImpl;


public class ObjectsListService {
	
	public static List<?> ObjectsList(Class<?> clazz) {
//		List<?> list = new ArrayList<>();
//		Session s=HibernateUtil.openSession();
//        s.beginTransaction();
//        list = s.createQuery("from " + clazz.getName()).list();
//        s.close();
//        return list;
		
		DAOListImpl dao = new DAOListImpl();
		return dao.list(clazz);
		
	}
	
	public static List<?> ObjectsCriterionList(Class<?> clazz, Criterion criterion) {
		DAOListImpl dao = new DAOListImpl();
		return dao.restrictionList(clazz, criterion);
	}
}

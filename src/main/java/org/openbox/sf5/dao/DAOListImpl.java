package org.openbox.sf5.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.openbox.sf5.db.ConnectionManager;

@Named
@ApplicationScoped
public class DAOListImpl implements DAOList, Serializable {

	private static final long serialVersionUID = -4127115450739908412L;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public <T> List<T> list(Class<T> type) {

		List<T> list = new ArrayList<>();

		Session s = cm.getSessionFactroy().openSession();
		// Session s = cm.getSessionFactroy().getCurrentSession();

		Transaction trans = null;
		try {

			trans = s.beginTransaction();
			list = s.createQuery("from " + type.getName()).list();
			trans.commit();
		}

		catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			throw e;
		}

		finally {
			s.close();
		}
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public <T> List<T> restrictionList(Class<T> type, Criterion criterion) {

		Session session = cm.getSessionFactroy().openSession();
		// Session session = cm.getSessionFactroy().getCurrentSession();

		List<T> returnedList = null;
		Transaction trans = null;
		try {
			Criteria criteria = session.createCriteria(type).add(criterion);
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY); // kill
																						// //
																						// duplicates

			returnedList = criteria.list();
		} catch (Exception e) {
			throw e;
		}

		finally {
			session.close();
		}

		return returnedList;
	}

//	@Inject
	@EJB
	private ConnectionManager cm;

}

package org.openbox.sf5.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.db.CarrierFrequency;
import org.openbox.sf5.db.HibernateUtil;
import org.openbox.sf5.db.KindsOfPolarization;
import org.openbox.sf5.db.RangesOfDVB;
import org.openbox.sf5.db.TheDVBRangeValues;
import org.openbox.sf5.db.ValueOfTheCarrierFrequency;
import org.openbox.sf5.service.ObjectsController;

@Named
@ApplicationScoped
public class TableFiller implements Serializable {

	private static final long serialVersionUID = 8464537239822198552L;

	public TableFiller() {

		List<RangesOfDVB> list = new ArrayList<RangesOfDVB>();
		list.add(RangesOfDVB.C);
		list.add(RangesOfDVB.Ku);

		TheDVBRangeValues newRecord = null;

		for (RangesOfDVB e : list) {

			// TheDVBRangeValues record = (TheDVBRangeValues)
			// service.select("db.TheDVBRangeValues", e);
			// List<Book>
			// book=(List<Book>)session.createCriteria(Book.class).createAlias("student",
			// "st").add(Restrictions.eq("st.name", "Maxim")).list();
			Session session = HibernateUtil.openSession();
			List<TheDVBRangeValues> rec = session.createCriteria(TheDVBRangeValues.class)
					.add(Restrictions.eq("RangeOfDVB", e)).list();

			if (rec.isEmpty()) {

				if (e.equals(RangesOfDVB.C)) {
					newRecord = new TheDVBRangeValues(e, 3400, 4200);

				}

				if (e.equals(RangesOfDVB.Ku)) {
					newRecord = new TheDVBRangeValues(e, 10700, 12750);

				}

				ObjectsController.add(newRecord);
			}

		}

		Session session = HibernateUtil.openSession();
		ValueOfTheCarrierFrequency value = null;
		List<ValueOfTheCarrierFrequency> rec = null;

		rec = session.createCriteria(ValueOfTheCarrierFrequency.class)
				.add(Restrictions.eq("TypeOfCarrierFrequency", CarrierFrequency.Lower))
				.add(Restrictions.eq("Polarization", KindsOfPolarization.Pie)).list();
		if (rec.isEmpty()) {
			value = new ValueOfTheCarrierFrequency(CarrierFrequency.Lower, KindsOfPolarization.Pie, 10700, 11699);
			ObjectsController.add(value);
		}

		rec = session.createCriteria(ValueOfTheCarrierFrequency.class)
				.add(Restrictions.eq("TypeOfCarrierFrequency", CarrierFrequency.Lower))
				.add(Restrictions.eq("Polarization", KindsOfPolarization.Linear)).list();

		if (rec.isEmpty()) {
			value = new ValueOfTheCarrierFrequency(CarrierFrequency.Lower, KindsOfPolarization.Linear, 10700, 11699);
			ObjectsController.add(value);
		}

		rec = session.createCriteria(ValueOfTheCarrierFrequency.class)
				.add(Restrictions.eq("TypeOfCarrierFrequency", CarrierFrequency.Top))
				.add(Restrictions.eq("Polarization", KindsOfPolarization.Linear)).list();

		if (rec.isEmpty()) {
			value = new ValueOfTheCarrierFrequency(CarrierFrequency.Top, KindsOfPolarization.Linear, 11700, 12750);
			ObjectsController.add(value);
		}

		rec = session.createCriteria(ValueOfTheCarrierFrequency.class)
				.add(Restrictions.eq("TypeOfCarrierFrequency", CarrierFrequency.CRange))
				.add(Restrictions.eq("Polarization", KindsOfPolarization.Linear)).list();

		if (rec.isEmpty()) {
			value = new ValueOfTheCarrierFrequency(CarrierFrequency.CRange, KindsOfPolarization.Linear, 3400, 4200);
			ObjectsController.add(value);

		}

		rec = session.createCriteria(ValueOfTheCarrierFrequency.class)
				.add(Restrictions.eq("TypeOfCarrierFrequency", CarrierFrequency.TopPie))
				.add(Restrictions.eq("Polarization", KindsOfPolarization.Pie)).list();

		if (rec.isEmpty()) {
			value = new ValueOfTheCarrierFrequency(CarrierFrequency.TopPie, KindsOfPolarization.Pie, 11700, 12750);
			ObjectsController.add(value);

		}

	}
}

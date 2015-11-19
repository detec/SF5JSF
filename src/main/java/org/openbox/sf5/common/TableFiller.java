package org.openbox.sf5.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.model.CarrierFrequency;
import org.openbox.sf5.model.KindsOfPolarization;
import org.openbox.sf5.model.RangesOfDVB;
import org.openbox.sf5.model.TheDVBRangeValues;
import org.openbox.sf5.model.ValueOfTheCarrierFrequency;
import org.openbox.sf5.service.ObjectsController;

@Named
@ApplicationScoped
public class TableFiller implements Serializable {
	@Inject
	private ObjectsController contr;

	@Inject
	private ConnectionManager cm;

	private static final long serialVersionUID = 8464537239822198552L;

	public TableFiller() {

	}

	public ObjectsController getContr() {
		return contr;
	}

	public void setContr(ObjectsController contr) {
		this.contr = contr;
	}

	public ConnectionManager getCm() {
		return cm;
	}

	public void setCm(ConnectionManager cm) {
		this.cm = cm;
	}

	public void init() {

		List<RangesOfDVB> list = new ArrayList<RangesOfDVB>();
		list.add(RangesOfDVB.C);
		list.add(RangesOfDVB.Ku);

		TheDVBRangeValues newRecord = null;

		Session session = cm.getSessionFactroy().openSession();

		for (RangesOfDVB e : list) {

			List<TheDVBRangeValues> rec = session.createCriteria(TheDVBRangeValues.class)
					.add(Restrictions.eq("RangeOfDVB", e)).list();

			if (rec.isEmpty()) {

				if (e.equals(RangesOfDVB.C)) {
					newRecord = new TheDVBRangeValues(e, 3400, 4200);

				}

				if (e.equals(RangesOfDVB.Ku)) {
					newRecord = new TheDVBRangeValues(e, 10700, 12750);

				}

				contr.add(newRecord);
			}

		}

		// Session session = HibernateUtil.openSession();
		ValueOfTheCarrierFrequency value = null;
		List<ValueOfTheCarrierFrequency> rec = null;

		rec = session.createCriteria(ValueOfTheCarrierFrequency.class)
				.add(Restrictions.eq("TypeOfCarrierFrequency", CarrierFrequency.Lower))
				.add(Restrictions.eq("Polarization", KindsOfPolarization.Pie)).list();
		if (rec.isEmpty()) {
			value = new ValueOfTheCarrierFrequency(CarrierFrequency.Lower, KindsOfPolarization.Pie, 10700, 11699);
			contr.add(value);
		}

		rec = session.createCriteria(ValueOfTheCarrierFrequency.class)
				.add(Restrictions.eq("TypeOfCarrierFrequency", CarrierFrequency.Lower))
				.add(Restrictions.eq("Polarization", KindsOfPolarization.Linear)).list();

		if (rec.isEmpty()) {
			value = new ValueOfTheCarrierFrequency(CarrierFrequency.Lower, KindsOfPolarization.Linear, 10700, 11699);
			contr.add(value);
		}

		rec = session.createCriteria(ValueOfTheCarrierFrequency.class)
				.add(Restrictions.eq("TypeOfCarrierFrequency", CarrierFrequency.Top))
				.add(Restrictions.eq("Polarization", KindsOfPolarization.Linear)).list();

		if (rec.isEmpty()) {
			value = new ValueOfTheCarrierFrequency(CarrierFrequency.Top, KindsOfPolarization.Linear, 11700, 12750);
			contr.add(value);
		}

		rec = session.createCriteria(ValueOfTheCarrierFrequency.class)
				.add(Restrictions.eq("TypeOfCarrierFrequency", CarrierFrequency.CRange))
				.add(Restrictions.eq("Polarization", KindsOfPolarization.Linear)).list();

		if (rec.isEmpty()) {
			value = new ValueOfTheCarrierFrequency(CarrierFrequency.CRange, KindsOfPolarization.Linear, 3400, 4200);
			contr.add(value);

		}

		rec = session.createCriteria(ValueOfTheCarrierFrequency.class)
				.add(Restrictions.eq("TypeOfCarrierFrequency", CarrierFrequency.TopPie))
				.add(Restrictions.eq("Polarization", KindsOfPolarization.Pie)).list();

		if (rec.isEmpty()) {
			value = new ValueOfTheCarrierFrequency(CarrierFrequency.TopPie, KindsOfPolarization.Pie, 11700, 12750);
			contr.add(value);

		}

		session.close();

	}
}

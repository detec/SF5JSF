package org.openbox.sf5.application;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.service.ObjectsListService;

@Named("transpondersListController")
@ViewScoped
public class TranspondersListController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1000488396477162309L;

	private Satellites filterSatellite;

	private List<Transponders> TranspondersList;

	public Satellites getFilterSatellite() {
		return filterSatellite;
	}

	public void setFilterSatellite(Satellites filterSatellite) {
		this.filterSatellite = filterSatellite;
	}

	public List<Transponders> getTranspondersList() {
		if (filterSatellite != null) {
			Criterion criterion = Restrictions.eq("Satellite",
					filterSatellite);
			//return (List<Transponders>) ObjectsListService
			TranspondersList = (List<Transponders>) ObjectsListService
					.ObjectsCriterionList(Transponders.class, criterion);
		} else {
//			return (List<Transponders>) ObjectsListService
//					.ObjectsList(Transponders.class);
			TranspondersList = (List<Transponders>) ObjectsListService
					.ObjectsList(Transponders.class);
		}

		return TranspondersList;
	}

	public List<Satellites> getSatellites() {
		return (List<Satellites>) ObjectsListService
				.ObjectsList(Satellites.class);
	}

}

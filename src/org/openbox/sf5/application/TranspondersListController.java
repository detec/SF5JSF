package org.openbox.sf5.application;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named("transpondersListController")
@RequestScoped
public class TranspondersListController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1000488396477162309L;

	@Inject
	private LoginBean loginBean;

	public boolean isSelectionMode() {
		return SelectionMode;
	}

	public void setSelectionMode(boolean selectionMode) {
		SelectionMode = selectionMode;
	}

	private boolean SelectionMode = false;

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	private Satellites filterSatellite;

	public void setTranspondersList(List<Transponders> transpondersList) {
		TranspondersList = transpondersList;
	}

	private List<Transponders> TranspondersList;

	public Satellites getFilterSatellite() {
		return filterSatellite;
	}

	public void setFilterSatellite(Satellites filterSatellite) {
		this.filterSatellite = filterSatellite;
	}

	@PostConstruct
	public void init() {

		if (loginBean.getFilterSatId() != 0) {
			ObjectsController contr = new ObjectsController();
			filterSatellite = ((Satellites) contr.select(Satellites.class,
					loginBean.getFilterSatId()));

		}

		if (filterSatellite != null) {
			Criterion criterion = Restrictions.eq("Satellite", filterSatellite);
			// return (List<Transponders>) ObjectsListService
			TranspondersList = (List<Transponders>) ObjectsListService
					.ObjectsCriterionList(Transponders.class, criterion);
		} else {
			// return (List<Transponders>) ObjectsListService
			// .ObjectsList(Transponders.class);
			TranspondersList = (List<Transponders>) ObjectsListService
					.ObjectsList(Transponders.class);
		}
	}

	public List<Transponders> getTranspondersList() {

		return TranspondersList;
	}

	public List<Satellites> getSatellites() {
		return (List<Satellites>) ObjectsListService
				.ObjectsList(Satellites.class);
	}

}

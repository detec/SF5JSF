package org.openbox.sf5.json.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.common.JsonObjectFiller;
import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named
@SessionScoped
public class TranspondersJsonizer implements Serializable {

	public String getTranspondersByArbitraryFilter(String fieldName, String typeValue) {
		String returnString = "";
		Criterion criterion = criterionService.getCriterionByClassFieldAndStringValue(Transponders.class, fieldName,
				typeValue);

		if (criterion == null) {
			return returnString;
		}

		List<Transponders> transList = (List<Transponders>) listService.ObjectsCriterionList(Transponders.class,
				criterion);

		returnString = JsonObjectFiller.getJsonFromObjectsList(transList);

		return returnString;

	}

	public String getTranspondersBySatelliteId(long satId) {
		String returnString = "";

		Satellites filterSatellite = (Satellites) contr.select(Satellites.class, satId);
		if (filterSatellite == null) {
			return returnString;
		}

		Criterion criterion = Restrictions.eq("Satellite", filterSatellite);

		List<Transponders> transList = (List<Transponders>) listService.ObjectsCriterionList(Transponders.class,
				criterion);

		returnString = JsonObjectFiller.getJsonFromObjectsList(transList);

		return returnString;

	}

	public String getTransponders() {
		List<Transponders> transList = (List<Transponders>) listService.ObjectsList(Transponders.class);

		String result = JsonObjectFiller.getJsonFromObjectsList(transList);

		return result;
	}

	@Inject
	private CriterionService criterionService;

	public CriterionService getCriterionService() {
		return criterionService;
	}

	public void setCriterionService(CriterionService criterionService) {
		this.criterionService = criterionService;
	}

	@Inject
	private ObjectsListService listService;

	@Inject
	private ObjectsController contr;

	public ObjectsController getContr() {
		return contr;
	}

	public void setContr(ObjectsController contr) {
		this.contr = contr;
	}

	public ObjectsListService getListService() {
		return listService;
	}

	public void setListService(ObjectsListService listService) {
		this.listService = listService;
	}

	private static final long serialVersionUID = 3988350842571407500L;

}

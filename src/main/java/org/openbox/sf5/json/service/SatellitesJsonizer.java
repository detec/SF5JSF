package org.openbox.sf5.json.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Criterion;
import org.openbox.sf5.common.JsonObjectFiller;
import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectsListService;

@Named
@SessionScoped
public class SatellitesJsonizer implements Serializable {

	public String getSatellitesList() {
		List<Satellites> satList = (List<Satellites>) listService.ObjectsList(Satellites.class);
		String result = JsonObjectFiller.getJsonFromObjectsList(satList);

		return result;
	}

	public String getSatellitesByArbitraryFilter(String fieldName, String typeValue) {
		String returnString = "";

		Criterion criterion = criterionService.getCriterionByClassFieldAndStringValue(Satellites.class, fieldName,
				typeValue);
		List<Satellites> satList = (List<Satellites>) listService.ObjectsCriterionList(Satellites.class, criterion);
		if (criterion == null) {
			return returnString;
		}
		returnString = JsonObjectFiller.getJsonFromObjectsList(satList);

		return returnString;
	}

	public CriterionService getCriterionService() {
		return criterionService;
	}

	public void setCriterionService(CriterionService criterionService) {
		this.criterionService = criterionService;
	}

	@Inject
	private ObjectsListService listService;

	public ObjectsListService getListService() {
		return listService;
	}

	public void setListService(ObjectsListService listService) {
		this.listService = listService;
	}

	private static final long serialVersionUID = 3401682206534536724L;

	@Inject
	private CriterionService criterionService;

}

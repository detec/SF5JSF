package org.openbox.sf5.json.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonObjectBuilder;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.common.JsonObjectFiller;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.model.Settings;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectsListService;

@Named
@SessionScoped
public class SettingsJsonizer implements Serializable {

	public String getSettingsByArbitraryFilter(String fieldName, String typeValue, String login) {
		String returnString = "";

		Criterion userCriterion = criterionService.getUserCriterion(login, Settings.class);
		if (userCriterion == null) {
			return returnString;
		}

		// building arbitrary criterion
		Criterion arbitraryCriterion = criterionService.getCriterionByClassFieldAndStringValue(Settings.class,
				fieldName, typeValue);

		if (arbitraryCriterion == null) {
			return returnString;
		}

		Session session = cm.getSessionFactroy().openSession();
		Criteria criteria = session.createCriteria(Settings.class).add(userCriterion).add(arbitraryCriterion);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		List<Settings> records = criteria.list();

		returnString = JsonObjectFiller.getJsonFromObjectsList(records);

		return returnString;
	}

	public String getSettingsByUserLogin(String login) {
		String returnString = "";

		Criterion userCriterion = criterionService.getUserCriterion(login, Settings.class);
		if (userCriterion == null) {
			return returnString;
		}

		List<Settings> settList = listService.ObjectsCriterionList(Settings.class, userCriterion);

		returnString = JsonObjectFiller.getJsonFromObjectsList(settList);

		return returnString;
	}

	public String getSettingById(long settingId, String login) {
		String returnString = "";

		Criterion userCriterion = criterionService.getUserCriterion(login, Settings.class);
		if (userCriterion == null) {
			return returnString;
		}

		Criterion settingIdCriterion = Restrictions.eq("id", settingId);

		Session session = cm.getSessionFactroy().openSession();
		List<Settings> records = session.createCriteria(Settings.class).add(userCriterion).add(settingIdCriterion)
				.list();

		Settings settingsObject = null;

		if (records.size() == 0) {
			// There is no such setting with username
			return returnString;
		} else {
			settingsObject = records.get(0);
		}

		// returning setting as JSON Object.
		JsonObjectBuilder transJOB;
		try {
			transJOB = JsonObjectFiller.getJsonObjectBuilderFromClassInstance(settingsObject);

		} catch (IllegalArgumentException | IllegalAccessException e) {
			return returnString;
		}

		returnString = commonJsonizer.JSonObjectBuilderToString(transJOB);

		return returnString;

	}

	public ConnectionManager getCm() {
		return cm;
	}

	public void setCm(ConnectionManager cm) {
		this.cm = cm;
	}

	public ObjectsListService getListService() {
		return listService;
	}

	public void setListService(ObjectsListService listService) {
		this.listService = listService;
	}

	public CriterionService getCriterionService() {
		return criterionService;
	}

	public void setCriterionService(CriterionService criterionService) {
		this.criterionService = criterionService;
	}

	@Inject
	private ConnectionManager cm;

	@Inject
	private ObjectsListService listService;

	@Inject
	private CriterionService criterionService;

	@Inject
	private CommonJsonizer commonJsonizer;

	public CommonJsonizer getCommonJsonizer() {
		return commonJsonizer;
	}

	public void setCommonJsonizer(CommonJsonizer commonJsonizer) {
		this.commonJsonizer = commonJsonizer;
	}

	private static final long serialVersionUID = -1067277140349379022L;

}

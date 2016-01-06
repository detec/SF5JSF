package org.openbox.sf5.json.service;

import java.io.IOException;
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
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Named
@SessionScoped
public class SettingsJsonizer implements Serializable {

	public int saveNewSetting(Settings setting) {
		long id = setting.getId();
		// if we receive non-empty id
		if (id != 0) {
			// return HttpStatus.CONFLICT;
			return 409;
		}
		objectsController.saveOrUpdate(setting);
		return 201; // CREATED
	}

	public int saveNewSetting(String settingJson) throws JsonParseException, JsonMappingException, IOException {
		// prepare mapper.
		JsonObjectFiller JsonOF = new JsonObjectFiller();
		JsonOF.configureMapper();

		Settings readSetting = JsonOF.objectMapper.readValue(settingJson, Settings.class);
		int result = saveNewSetting(readSetting);

		return result;
	}

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
		List<Settings> records = getListOfSettingsByUserAndArbitraryCriterion(userCriterion, arbitraryCriterion);

		returnString = JsonObjectFiller.getJsonFromObjectsList(records);

		return returnString;
	}

	public List<Settings> getListOfSettingsByUserAndArbitraryCriterion(Criterion userCriterion,
			Criterion arbitraryCriterion) {
		Session session = cm.getSessionFactroy().openSession();
		Criteria criteria = session.createCriteria(Settings.class).add(userCriterion).add(arbitraryCriterion);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

		@SuppressWarnings("unchecked")
		List<Settings> records = criteria.list();
		session.close();

		return records;
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

		List<Settings> records = getListOfSettingsByUserAndArbitraryCriterion(userCriterion, settingIdCriterion);

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
	private ObjectsController objectsController;

	public ObjectsController getObjectsController() {
		return objectsController;
	}

	public void setObjectsController(ObjectsController objectsController) {
		this.objectsController = objectsController;
	}

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

package org.openbox.sf5.json.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Criterion;
import org.openbox.sf5.model.Users;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named
@SessionScoped
public class UsersJsonizer implements Serializable {

	// returns false if there is no such user.
	public long checkIfUsernameExists(String typeValue) {
		long result = 0;
		Criterion criterion = criterionService.getCriterionByClassFieldAndStringValue(Users.class, "Login", typeValue);

		if (criterion == null) {
			return 0;
		}
		List<Users> userList = listService.ObjectsCriterionList(Users.class, criterion);
		if (userList.size() == 0) {
			return 0;
		} else {

			result = userList.get(0).getId();
		}

		return result;
	}

	public int saveNewUser(Users user) {
		long id = user.getId();
		// if we receive non-empty id
		if (id != 0) {
			return 409;
		}
		objectsController.saveOrUpdate(user);
		return 201;

	}

	public String getUserByLogin(String typeValue) {
		String returnString = "";
		Criterion criterion = criterionService.getCriterionByClassFieldAndStringValue(Users.class, "Login", typeValue);

		if (criterion == null) {
			return returnString;
		}

		List<Users> userList = listService.ObjectsCriterionList(Users.class, criterion);
		if (userList.size() == 0) {
			return returnString;
		}

		Users returnUser = userList.get(0);
		returnString = commonJsonizer.buildJsonStringByTypeAndId(returnUser.getId(), Users.class);

		return returnString;
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
	private ObjectsController objectsController;

	public ObjectsController getObjectsController() {
		return objectsController;
	}

	public void setObjectsController(ObjectsController objectsController) {
		this.objectsController = objectsController;
	}

	public ObjectsListService getListService() {
		return listService;
	}

	public void setListService(ObjectsListService listService) {
		this.listService = listService;
	}

	@Inject
	private CommonJsonizer commonJsonizer;

	public CommonJsonizer getCommonJsonizer() {
		return commonJsonizer;
	}

	public void setCommonJsonizer(CommonJsonizer commonJsonizer) {
		this.commonJsonizer = commonJsonizer;
	}

	private static final long serialVersionUID = -2661572182283342066L;

}

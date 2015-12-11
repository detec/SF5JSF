package org.openbox.sf5.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.openbox.sf5.common.JsonObjectFiller;
import org.openbox.sf5.model.AbstractDbEntity;
import org.openbox.sf5.model.Users;

@Named
@ApplicationScoped
public class CriterionService implements Serializable {

	public <T extends AbstractDbEntity> Criterion getCriterionByClassFieldAndStringValue(Class<T> type,
			String fieldName, String typeValue) {
		Criterion criterion = null;

		// We have the following situation
		// 1. Field name is of primitive type. Then we use simple Criterion.
		// 2. Field is enum. Then it should be String representation of an enum.
		// 3. Field is String.
		// 4. Filed is entity class, retrieved from database. Then we select
		// object by id, that came as typeValue.

		Class<?> fieldClazz = JsonObjectFiller.getFieldClass(type, fieldName);
		// check if this field has some class, not null
		if (fieldClazz == null) {
			// Return empty criterion
			return criterion;
		}

		else if (fieldClazz.isPrimitive()) {
			criterion = Restrictions.eq(fieldName, Long.parseLong(typeValue));
		}

		// check that it is an enum
		else if (Enum.class.isAssignableFrom(fieldClazz)) {
			// must select from HashMap where key is String representation of
			// enum

			// http://stackoverflow.com/questions/1626901/java-enums-list-enumerated-values-from-a-class-extends-enum
			List<?> enumList = enum2list((Class<? extends Enum>) fieldClazz);
			HashMap<String, Object> hm = new HashMap<>();
			enumList.stream().forEach(t -> hm.put(t.toString(), t));

			// now get enum value by string representation
			criterion = Restrictions.eq(fieldName, hm.get(typeValue));
		}

		else if (fieldClazz == String.class) {
			// we build rather primitive criterion
			criterion = Restrictions.eq(fieldName, typeValue);
		}

		else {
			// it is a usual class
			T filterObject = objectsController.select((Class<T>) fieldClazz, Long.parseLong(typeValue));
			criterion = Restrictions.eq(fieldName, filterObject);

		}

		return criterion;
	}

	// gets criterion based on user login
	public <T extends AbstractDbEntity> Criterion getUserCriterion(String login, Class<T> type) {
		// Find out user id.
		SimpleExpression criterion = null;
		Criterion userCriterion = null;

		criterion = Restrictions.eq("Login", login);
		List<Users> usersList = listService.ObjectsCriterionList(Users.class, criterion);

		if (usersList.size() == 0) {
			return criterion;
		}

		String userIdToString = Long.toString(usersList.get(0).getId());

		// Let's filter by userId and settings id
		userCriterion = getCriterionByClassFieldAndStringValue(type, "User", userIdToString);

		return userCriterion;

	}

	@SuppressWarnings("rawtypes")
	private static List<Enum> enum2list(Class<? extends Enum> cls) {
		return Arrays.asList(cls.getEnumConstants());
	}

	public ObjectsController getObjectsController() {
		return objectsController;
	}

	public void setObjectsController(ObjectsController objectsController) {
		this.objectsController = objectsController;
	}

	private static final long serialVersionUID = -2669096886833468746L;

	@Inject
	private ObjectsController objectsController;

	@Inject
	private ObjectsListService listService;

	public ObjectsListService getListService() {
		return listService;
	}

	public void setListService(ObjectsListService listService) {
		this.listService = listService;
	}

}

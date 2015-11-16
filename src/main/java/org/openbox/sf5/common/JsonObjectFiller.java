package org.openbox.sf5.common;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.core.Response;

import org.hibernate.collection.internal.PersistentList;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.openbox.sf5.db.Users;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

// This class is intended for static functions that convert DB objects into JSON.
public class JsonObjectFiller {

	public static <T> Criterion getUserCriterion(String login, ObjectsListService listService, ObjectsController contr,
			Class<T> type) {
		// Find out user id.
		SimpleExpression criterion = null;
		Criterion userCriterion = null;

		criterion = Restrictions.eq("Login", login);
		List<Users> usersList = (List<Users>) listService.ObjectsCriterionList(Users.class, criterion);

		if (usersList.size() == 0) {
			return criterion;
		}

		String userIdToString = Long.toString(usersList.get(0).getId());

		// Let's filter by userId and settings id
		userCriterion = JsonObjectFiller.getCriterionByClassFieldAndStringValue(type, "User", userIdToString, contr);

		return userCriterion;

	}

	// seems to be the first correct implementation for hibernate mapping
	// projects using 1C mapping tool.
	public static <T, L> JsonObjectBuilder getJsonObjectBuilderFromClassInstance(T object)
			throws IllegalArgumentException, IllegalAccessException {
		Field fields[];
		fields = object.getClass().getDeclaredFields();

		JsonObjectBuilder JOB = Json.createObjectBuilder();
		// use reflection
		// arrayOfTransponders.add(arg0)
		for (int i = 0; i < fields.length; i++) {

			String fieldName = fields[i].getName();
			if (fieldName.equals("serialVersionUID")) {
				continue;
			}

			fields[i].setAccessible(true);

			// http://stackoverflow.com/questions/21120999/representing-null-in-json

			// here we need to check if a field is a PersistentList.
			if (fields[i].get(object) instanceof PersistentList) {
				// here we should serialize content of the list
				List<L> persistentList = (List<L>) fields[i].get(object);

				// String strValue = getJsonFromObjectsList(persistentList);

				JOB.add(fieldName, getJsonArray(persistentList));

			} else {
				if (fields[i].get(object) != null) {

					// checking if field is boolean
					if (fields[i].getType().equals(boolean.class)) {
						JsonValue strValue = ((boolean) fields[i].get(object) == true) ? JsonValue.TRUE
								: JsonValue.FALSE;
						JOB.add(fieldName, strValue);
					}

					// checking if it is Date class
					else if (fields[i].getType().equals(Timestamp.class)) {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
						formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
						String strValue = formatter.format(fields[i].get(object));
						JOB.add(fieldName, strValue);
					}

					// writing as JsonNumber
					else if (fields[i].getType().equals(long.class)) {
						long strValue = (long) fields[i].get(object);
						JOB.add(fieldName, strValue);
					}

					// other classes
					else {
						String strValue = fields[i].get(object).toString();
						JOB.add(fieldName, strValue);
					}

				} else {
					JsonValue strValue = JsonValue.NULL;
					JOB.add(fieldName, strValue);
				}
			}

		} // end of loop

		return JOB;
	}

	public static <T extends Object> Response buildResponseByTypeAndId(ObjectsController contr, long Id,
			Class<T> type) {

		T DBobject = (T) contr.select(type, Id);

		JsonObjectBuilder transJOB;
		String result = "";
		try {
			transJOB = JsonObjectFiller.getJsonObjectBuilderFromClassInstance(DBobject);

			JsonObject JObject = transJOB.build();
			result = JObject.toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(result).build();
	}

	// This method returns class from the field name
	@SuppressWarnings("unchecked")
	public static <T extends Object> Class<?> getFieldClass(Class<T> type, String fieldName) {
		Field fields[];
		fields = type.getDeclaredFields();

		List<Field> fieldList = Arrays.asList(fields);
		Class<T> clazz = null;
		List<Class<T>> classList = new ArrayList<Class<T>>();

		// find field with the given name and return its class
		fieldList.stream().filter(t -> t.getName().equals(fieldName)).forEach(t -> {
			classList.add((Class<T>) t.getType());
		});

		if (classList.size() == 1) {
			clazz = classList.get(0);
		}

		// o.getClass().getField("fieldName").getType().isPrimitive(); for
		// primitives
		return clazz;
	}

	public static <T> JsonArray getJsonArray(List<T> objList) {
		JsonArrayBuilder arrayOfObjects = Json.createArrayBuilder();
		objList.stream().forEach(t -> {

			try {
				JsonObjectBuilder trans = JsonObjectFiller.getJsonObjectBuilderFromClassInstance(t);
				arrayOfObjects.add(trans);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		JsonArray JObject = arrayOfObjects.build();
		return JObject;
	}

	public static <T> String getJsonFromObjectsList(List<T> objList) {
		JsonArray JObject = getJsonArray(objList);

		// JSON pretty formatting - Taken from:
		// glassfish4\docs\javaee-tutorial\examples\web\jsonp\jsonpmodel\src\main\java\javaeetutorial
		// \jsonpmodel\ObjectModelBean.java
		// http://stackoverflow.com/questions/23007567/java-json-pretty-print-javax-json
		Map<String, Boolean> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonWriterFactory factory = Json.createWriterFactory(config);

		// http://blog.eisele.net/2013/02/test-driving-java-api-for-processing.html
		StringWriter sw = new StringWriter();
		try (JsonWriter jw = factory.createWriter(sw)) {
			jw.writeArray(JObject);
		}

		return sw.toString();

	}

	@SuppressWarnings("rawtypes")
	public static List<Enum> enum2list(Class<? extends Enum> cls) {
		return Arrays.asList(cls.getEnumConstants());
	}

	public static <T> Criterion getCriterionByClassFieldAndStringValue(Class<T> type, String fieldName,
			String typeValue, ObjectsController contr) {
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
			Object filterObject = contr.select(fieldClazz, Long.parseLong(typeValue));
			criterion = Restrictions.eq(fieldName, filterObject);

		}

		return criterion;
	}
}

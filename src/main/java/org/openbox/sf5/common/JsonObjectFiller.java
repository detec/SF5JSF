package org.openbox.sf5.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.service.ObjectsController;

public class JsonObjectFiller {

	public static <T> JsonObjectBuilder getJsonObjectBuilderFromClassInstance(T object)
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
			String strValue;

			strValue = fields[i].get(object).toString();
			JOB.add(fieldName, strValue);

		} // end of loop

		return JOB;
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

	// ? extends Object

	public static <T> String getJsonFromObjectsList(List<T> objList) {

		JsonObjectBuilder listObject = Json.createObjectBuilder();
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

		// http://stackoverflow.com/questions/2390662/java-how-do-i-get-a-class-literal-from-a-generic-type
		// Type typeOfListOfFoo = new TypeToken<List<Foo>>(){}.getType()

//		Type typeOfListOfFoo = new TypeToken<List<T>>() {
//		}.getType();

		// putting class name, Transponders, Satellites etc.
		//listObject.add(typeOfListOfFoo.getTypeName(), arrayOfObjects);

	//	Type typeOfListOfFoo = new TypeToken<objList.>(){}.getType();

		// quick solution
		String arrayName = "";
		if (objList.size() > 0) {
			arrayName = objList.get(0).getClass().getSimpleName();
		}
		else {
			arrayName = "empty";
		}

		listObject.add(arrayName, arrayOfObjects);

		JsonObject JObject = listObject.build();
		String result = JObject.toString();
		return result;
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

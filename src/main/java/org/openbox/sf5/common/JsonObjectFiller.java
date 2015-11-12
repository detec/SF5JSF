package org.openbox.sf5.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

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

	@SuppressWarnings("rawtypes")
	public static List<Enum> enum2list(Class<? extends Enum> cls) {
		return Arrays.asList(cls.getEnumConstants());
	}
}

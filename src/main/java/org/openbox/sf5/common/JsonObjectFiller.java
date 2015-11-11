package org.openbox.sf5.common;

import java.lang.reflect.Field;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class JsonObjectFiller {

	public static <T> JsonObjectBuilder getJsonObjectBuilderFromClassInstance(T object) throws IllegalArgumentException, IllegalAccessException {
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
}

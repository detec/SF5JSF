package org.openbox.sf5.json.service;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

import org.openbox.sf5.common.JsonObjectFiller;
import org.openbox.sf5.service.ObjectsController;

@Named
@SessionScoped
public class CommonJsonizer implements Serializable {

	public <T extends Object> String buildJsonStringByTypeAndId(long Id, Class<T> type) {
		String result = "";

		T DBobject = (T) contr.select(type, Id);
		if (DBobject == null) {
			return result;
		}
		// may return null
		JsonObjectBuilder transJOB;

		try {
			transJOB = JsonObjectFiller.getJsonObjectBuilderFromClassInstance(DBobject);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return result;
		}

		JsonObject JObject = transJOB.build();

		Map<String, Boolean> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonWriterFactory factory = Json.createWriterFactory(config);

		// http://blog.eisele.net/2013/02/test-driving-java-api-for-processing.html
		StringWriter sw = new StringWriter();
		try (JsonWriter jw = factory.createWriter(sw)) {
			jw.writeObject(JObject);
		}

		result = sw.toString();

		return result;
	}

	public String JSonObjectBuilderToString(JsonObjectBuilder transJOB) {
		String returnString = "";

		JsonObject JObject = transJOB.build();

		Map<String, Boolean> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonWriterFactory factory = Json.createWriterFactory(config);

		// http://blog.eisele.net/2013/02/test-driving-java-api-for-processing.html
		StringWriter sw = new StringWriter();
		try (JsonWriter jw = factory.createWriter(sw)) {
			jw.writeObject(JObject);
		}

		returnString = sw.toString();

		return returnString;
	}

	private static final long serialVersionUID = -6703918663028892352L;

	@Inject
	private ObjectsController contr;

	public ObjectsController getContr() {
		return contr;
	}

	public void setContr(ObjectsController contr) {
		this.contr = contr;
	}

}

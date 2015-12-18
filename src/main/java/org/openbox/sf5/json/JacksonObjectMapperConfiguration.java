package org.openbox.sf5.json;

import org.openbox.sf5.common.JsonObjectFiller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonObjectMapperConfiguration {

	public static void configureMapper(ObjectMapper mapper) {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// from http://wiki.fasterxml.com/JacksonFAQDateHandling
		mapper.setDateFormat(JsonObjectFiller.getJsonDateFormatter());

		mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
		// mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
		// true);

		// http://stackoverflow.com/questions/4428109/jersey-jackson-json-date-format-serialization-how-to-change-the-format-or-us
		// it doesn't work
		// mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
		// true);

		// https://metabroadcast.com/blog/using-jackson-2-with-jersey
		 mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
		 false);
		// mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE,
		// true);
		// mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
		// true);

		// http://wiki.fasterxml.com/JacksonFAQDateHandling

	}

}

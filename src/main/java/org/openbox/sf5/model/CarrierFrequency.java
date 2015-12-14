package org.openbox.sf5.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CarrierFrequency {

	Lower("9750"), Top("10600"), CRange("5150"), TopPie("10750");

	private CarrierFrequency(String s) {
		value = s;
	}

	private final String value;

	@Override
	public String toString() {
		return value;
	}

	// http://stackoverflow.com/questions/31689107/deserializing-an-enum-with-jackson
	private static Map<String, CarrierFrequency> FORMAT_MAP = Stream.of(CarrierFrequency.values())
			.collect(Collectors.toMap(s -> s.value, Function.identity()));

	@JsonCreator // This is the factory method and must be static
	public static CarrierFrequency fromString(String string) {
		CarrierFrequency status = FORMAT_MAP.get(string);
		if (status == null) {
			throw new IllegalArgumentException(string + " has no corresponding	 value");
		}
		return status;
	}

}
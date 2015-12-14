package org.openbox.sf5.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum DVBStandards {

	DVBS("DVB-S"), DVBS2("DVB-S2");

	private DVBStandards(String s) {
		value = s;
	}

	private final String value;

	@Override
	public String toString() {
		return value;
	}

	// http://stackoverflow.com/questions/31689107/deserializing-an-enum-with-jackson
	private static Map<String, DVBStandards> FORMAT_MAP = Stream.of(DVBStandards.values())
			.collect(Collectors.toMap(s -> s.value, Function.identity()));

	@JsonCreator // This is the factory method and must be static
	public static DVBStandards fromString(String string) {
		DVBStandards status = FORMAT_MAP.get(string);
		if (status == null) {
			throw new IllegalArgumentException(string + " has no corresponding	 value");
		}
		return status;
	}

}
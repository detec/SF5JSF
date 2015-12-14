package org.openbox.sf5.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TypesOfFEC {

	_12("1-2"), _23("2-3"), _34("3-4"), _56("5-6"), _78("7-8"), _89("8-9"), _91("9-10"), _35("3-5");

	private TypesOfFEC(String s) {
		value = s;
	}

	private final String value;

	@Override
	public String toString() {
		return value;
	}

	// http://stackoverflow.com/questions/31689107/deserializing-an-enum-with-jackson
	private static Map<String, TypesOfFEC> FORMAT_MAP = Stream.of(TypesOfFEC.values())
			.collect(Collectors.toMap(s -> s.value, Function.identity()));

	@JsonCreator // This is the factory method and must be static
	public static TypesOfFEC fromString(String string) {
		TypesOfFEC status = FORMAT_MAP.get(string);
		if (status == null) {
			throw new IllegalArgumentException(string + " has no corresponding	 value");
		}
		return status;
	}

}
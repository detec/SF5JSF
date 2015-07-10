package org.openbox.sf5.db;

	public enum KindsOfPolarization{

Linear("Linear"), Pie("Pie");

	private KindsOfPolarization(String s) {
	value = s;
	}

	private final String value;

	@Override
	public String toString() {
		return value;
	}


}
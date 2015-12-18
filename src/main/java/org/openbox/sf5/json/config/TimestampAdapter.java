package org.openbox.sf5.json.config;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.openbox.sf5.common.JsonObjectFiller;

public class TimestampAdapter extends XmlAdapter<String, Timestamp> {

	private SimpleDateFormat dateFormat = JsonObjectFiller.getJsonDateFormatter();

	@Override
	public String marshal(Timestamp v) throws Exception {
		return dateFormat.format(v);
	}

	@Override
	public Timestamp unmarshal(String v) throws Exception {
		return (Timestamp) dateFormat.parse(v);
	}

}

package org.openbox.sf5.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named("satelliteInfo")
@ApplicationScoped
public class SatelliteInfo {

	private long satelliteId;

	private String satelliteName;

	public String getSatelliteName() {
		ObjectsController contr = new ObjectsController();

		return ((Satellites) contr.select(Satellites.class, satelliteId))
				.getName();
	}

	public void setSatelliteName(String satelliteName) {
		this.satelliteName = satelliteName;
	}

	private List<Satellites> satellitesList;

	private List<String> satelliteChoices;

	public long getSatelliteId() {
		return satelliteId;
	}

	public void setSatelliteId(long satelliteId) {
		this.satelliteId = satelliteId;
	}

	public List<Satellites> getSatellitesList() {
		return satellitesList;
	}

	public void setSatellitesList(List<Satellites> satellitesList) {
		this.satellitesList = satellitesList;
	}

	public List<String> getSatelliteChoices() {
		return satelliteChoices;
	}

	public void setSatelliteChoices(List<String> satelliteChoices) {
		this.satelliteChoices = satelliteChoices;
	}

	public Map<Long, Satellites> getSatellitesMap() {
		return satellitesMap;
	}

	public void setSatellitesMap(Map<Long, Satellites> satellitesMap) {
		this.satellitesMap = satellitesMap;
	}

	private Map<Long, Satellites> satellitesMap;

	public Satellites getSatellite() {
		return this.satellitesMap.get(this.satelliteId);

	}

	public SatelliteInfo() {
		satelliteChoices = new ArrayList<>();

		satellitesMap = new HashMap<>();

		satellitesList = (List<Satellites>) ObjectsListService
				.ObjectsList(Satellites.class);
		if (satellitesList.isEmpty()) {
			this.satelliteId = 0;
			this.satelliteName = "";
		}

		else {
			satelliteName = satellitesList.get(0).getName();
		}

		for (Satellites sat : satellitesList) {
			String aSatName = sat.getName();
			satelliteChoices.add(aSatName);
			satellitesMap.put(new Long(sat.getId()), sat);
		}
	}

}

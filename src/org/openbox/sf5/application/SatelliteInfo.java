package org.openbox.sf5.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.openbox.sf5.db.Satellites;
import org.openbox.sf5.service.ObjectsListService;

@Named("satelliteInfo")
@ApplicationScoped
public class SatelliteInfo {

	private String satelliteName;

	private List<Satellites> satellitesList;

	private List<String> satelliteChoices;

	private Map<Long, Satellites> satellitesMap;

	public SatelliteInfo() {
		satelliteChoices = new ArrayList<>();

		satellitesMap = new HashMap<>();

		satellitesList = (List<Satellites>) ObjectsListService.ObjectsList(Satellites.class);
		if (satellitesList.isEmpty()) {
			satelliteName = "";
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

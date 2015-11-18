package org.openbox.sf5.json.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openbox.sf5.db.Satellites;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(JUnit4.class)
public class SatellitesJsonizerTests extends AbstractJsonizerTest {

	@Test
	public void shouldGetSatelliteById() throws JsonParseException, JsonMappingException, IOException {
		Satellites sat = new Satellites();
		 sat.setName("test");
		 contr.saveOrUpdate(sat); // as it is saved it should have id 1.

		 String result = commonJsonizer.buildJsonStringByTypeAndId(1, Satellites.class);

		 ObjectMapper mapper = new ObjectMapper();
		 Satellites readSat = mapper.readValue(result, Satellites.class);

		 assertEquals(1, readSat.getId());
		 assertEquals("test", readSat.getName());

	}


	 private SatellitesJsonizer satellitesJsonizer;


	@Before
	public void setUp() {

		JsonizerTestSetup.setUp(cm, DAO, DAOList, service, contr, criterionService, listService, commonJsonizer);
		satellitesJsonizer = new SatellitesJsonizer();
		satellitesJsonizer.setCriterionService(criterionService);
		satellitesJsonizer.setListService(listService);


	}


}

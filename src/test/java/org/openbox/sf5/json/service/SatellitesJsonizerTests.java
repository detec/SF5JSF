package org.openbox.sf5.json.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openbox.sf5.model.Satellites;

@RunWith(JUnit4.class)
public class SatellitesJsonizerTests extends AbstractJsonizerTest {

	@Test
	public void shouldGetSatellitesByArbitraryFilter() {
		String result = satellitesJsonizer.getSatellitesByArbitraryFilter("Name", "4.8E");

//		List<Satellites> satList = mapper.readValue(result,
//				mapper.getTypeFactory().constructCollectionType(List.class, Satellites.class));
//
//		assertEquals(1, satList.size());
	}

	@Test
	public void shouldGetSatelliteById() {

		String result = commonJsonizer.buildJsonStringByTypeAndId(1, Satellites.class);

		// Satellites readSat = mapper.readValue(result, Satellites.class);
//		Satellites readSat = JAXB.unmarshal(result, Satellites.class);
//
//		assertEquals(1, readSat.getId());
//		assertEquals("4.8E", readSat.getName());

	}

	@Test
	public void shouldGetSatellitesList() {
		// create 2 satellites. This is the first test that is run.
		// It is not the first now.
		// create2Satellites();

		String result = satellitesJsonizer.getSatellitesList();

		// http://www.leveluplunch.com/java/examples/convert-json-array-to-arraylist-of-objects-jackson/
//		List<Satellites> satList = mapper.readValue(result,
//				mapper.getTypeFactory().constructCollectionType(List.class, Satellites.class));
//
//		assertEquals(3, satList.size());
	}

	// for reading Json

	private SatellitesJsonizer satellitesJsonizer;

	private void create2Satellites() {
		for (int i = 1; i < 3; i++) {

			Satellites sat = new Satellites();
			sat.setName("test" + Integer.toString(i));
			objectsController.saveOrUpdate(sat);
		}
	}

	// this method is run every time the test launches
	@Before
	public void setUp() {

		super.setUpAbstract();

		satellitesJsonizer = new SatellitesJsonizer();
		satellitesJsonizer.setCriterionService(criterionService);
		satellitesJsonizer.setListService(listService);

	}

}

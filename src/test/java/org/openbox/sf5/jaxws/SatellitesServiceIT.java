package org.openbox.sf5.jaxws;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openbox.sf5.wsmodel.Satellites;

// https://github.com/javaee-samples/javaee7-samples/blob/master/jaxws/jaxws-client/src/test/java/org/javaee7/jaxws/client/EBookStoreClientSampleTest.java

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SatellitesServiceIT extends AbstractWSTest {

	@Before
	public void setUp() throws Exception {
		setUpAbstract();
	}

	@Test
	public void getSatWithID1() {

		Satellites returnSatellite = SF5Port.getSatelliteById(1);
		assertEquals("4.8E", returnSatellite.getName());
	}

	@Test
	public void getAllSatellites() {
		List<Satellites> satList = SF5Port.getAllSatellites();
		assertEquals(3, satList.size());

	}

	@Test
	public void getSatellitesByArbitraryFilter() {
		List<Satellites> satList = SF5Port.getSatellitesByArbitraryFilter("Name", "13E");
		assertEquals(1, satList.size());

	}

}

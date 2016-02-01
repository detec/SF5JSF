package org.openbox.sf5.jaxws;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openbox.sf5.wsmodel.Satellites;
import org.openbox.sf5.wsmodel.WSException_Exception;

// https://github.com/javaee-samples/javaee7-samples/blob/master/jaxws/jaxws-client/src/test/java/org/javaee7/jaxws/client/EBookStoreClientSampleTest.java

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SatellitesServiceIT extends AbstractWSTest {

	public long satelliteId;

	@Before
	public void setUp() throws Exception {
		setUpAbstract();
	}

	@Test
	public void getAllSatellites() {
		List<Satellites> satList = null;
		try {
			satList = SF5Port.getAllSatellites();
		} catch (WSException_Exception e) {
			e.printStackTrace();
		}
		assertEquals(3, satList.size());

	}

	public long getSatelliteId() {
		List<Satellites> satList = null;
		try {
			satList = SF5Port.getSatellitesByArbitraryFilter("Name", "13E");
		} catch (WSException_Exception e) {
			e.printStackTrace();
		}
		assertEquals(1, satList.size());

		Satellites readSat = satList.get(0);
		assertThat(readSat instanceof Satellites);

		satelliteId = readSat.getId();

		return satelliteId;
	}

	@Test
	public void getSatellitesByArbitraryFilter() {

		Satellites returnSatellite = null;

		getSatelliteId();

		try {
			returnSatellite = SF5Port.getSatelliteById(satelliteId);
		} catch (WSException_Exception e) {

			e.printStackTrace();
		}
		assertEquals("13E", returnSatellite.getName());

	}

}

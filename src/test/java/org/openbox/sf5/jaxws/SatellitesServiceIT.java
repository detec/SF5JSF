package org.openbox.sf5.jaxws;

import java.net.URL;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

// https://github.com/javaee-samples/javaee7-samples/blob/master/jaxws/jaxws-client/src/test/java/org/javaee7/jaxws/client/EBookStoreClientSampleTest.java

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SatellitesServiceIT extends AbstractWSTest {

	// private SatellitesService satellitesService;

	private URL url;

	// private Satellites satPort;

	@Before
	public void setUp() throws Exception {
		loadProperties();

		url = new URL(appLocation + property.getProperty("context.path"));

		// satellitesService = new SatellitesService(new URL(url,
		// "SatellitesService?wsdl"),
		// new QName("http://sf5.openbox.org/satelliteservice/1.0",
		// "SatellitesService"));
		//
		// satPort = satellitesService.getSatellitesPort();
	}

	@Test
	public void getSatWithID1() {

		// Satellites_Type returnSatellite = satPort.getSatelliteById(1);
		// assertEquals("4.8E", returnSatellite.getName());
	}

	@Test
	public void getAllSatellites() {

		// GenericXMLListWrapper satWrapper = satPort.getAllSatellites();
		// List<Object> satList = satWrapper.getWrappedList();
		// assertEquals(3, satList.size());

	}

	@Test
	public void getSatellitesByArbitraryFilter() {

		// GenericXMLListWrapper satWrapper =
		// satPort.getSatellitesByArbitraryFilter("Name", "13E");
		// List<Object> satList = satWrapper.getWrappedList();
		// assertEquals(1, satList.size());

	}

}

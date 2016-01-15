package org.openbox.sf5.jaxws;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.openbox.sf5.wsmodel.Transponders;
import org.openbox.sf5.wsmodel.WSException_Exception;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TranspondersServiceIT extends AbstractWSTest {

	@Before
	public void setUp() throws Exception {
		setUpAbstract();
	}

	@Test
	public void shouldGetTranspondersByArbitraryFilter() {

		List<Transponders> transList = null;
		try {
			transList = SF5Port.getTranspondersByArbitraryFilter("Speed", "27500");
		} catch (WSException_Exception e) {
			e.printStackTrace();
		}

		assertThat(transList).isNotNull();
		assertThat(transList.size()).isGreaterThan(0);
	}

	@Test
	public void shouldGetTransponderById() {
		Transponders trans = null;
		try {
			trans = SF5Port.getTransponderById(1);
		} catch (WSException_Exception e) {
			e.printStackTrace();
		}
		assertThat(trans).isNotNull();
	}

	@Test
	public void shouldGetTranspondersBySatelliteId() {

		List<Transponders> transList = null;
		try {
			transList = SF5Port.getTranspondersBySatelliteId(1);
		} catch (WSException_Exception e) {
			e.printStackTrace();
		}
		assertThat(transList).isNotNull();
		assertThat(transList.size()).isGreaterThan(0);
	}

	@Test
	public void shouldGetAllTransponders() {

		List<Transponders> transList = null;
		try {
			transList = SF5Port.getTransponders();
		} catch (WSException_Exception e) {

			e.printStackTrace();
		}

		assertThat(transList).isNotNull();
		assertThat(transList.size()).isGreaterThan(0);
	}

}

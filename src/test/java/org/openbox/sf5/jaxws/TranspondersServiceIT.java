package org.openbox.sf5.jaxws;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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

	private Validator validator;

	@Before
	public void setUp() throws Exception {
		setUpAbstract();

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
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
		validateTranspondersList(transList);

		Transponders readTrans = transList.get(0);
		Transponders trans = null;
		try {
			trans = SF5Port.getTransponderById(readTrans.getId());
		} catch (WSException_Exception e) {
			e.printStackTrace();
		}
		assertThat(trans).isNotNull();

	}

	@Test
	public void shouldGetTranspondersBySatelliteId() throws Exception {

		SatellitesServiceIT satTest = new SatellitesServiceIT();
		satTest.setUp();

		List<Transponders> transList = null;

		try {
			transList = SF5Port.getTranspondersBySatelliteId(satTest.getSatelliteId());
		} catch (WSException_Exception e) {
			e.printStackTrace();
		}
		assertThat(transList).isNotNull();
		assertThat(transList.size()).isGreaterThan(0);
		validateTranspondersList(transList);
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
		validateTranspondersList(transList);
	}

	public void validateTranspondersList(List<Transponders> transList) {
		int[] resultArray = new int[1];

		transList.forEach(t -> {
			Set<ConstraintViolation<Transponders>> constraintViolations = validator.validate(t);
			resultArray[0] = resultArray[0] + constraintViolations.size();

		});

		assertEquals(0, resultArray[0]);
	}

}

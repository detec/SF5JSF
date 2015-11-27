package org.openbox.sf5.json.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openbox.sf5.model.Transponders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class TranspondersJsonizerTests extends AbstractJsonizerTest {

	private void checkTransListIsNotEmpty(String result) throws JsonParseException, JsonMappingException, IOException {
		List<Transponders> transList = mapper.readValue(result,
				mapper.getTypeFactory().constructCollectionType(List.class, Transponders.class));

		assertThat(transList.size()).isNotEqualTo(0);
	}

	@Test
	public void shouldgetTransponderById() throws JsonParseException, JsonMappingException, IOException {
		String result = commonJsonizer.buildJsonStringByTypeAndId(1, Transponders.class);

		Transponders readTrans = mapper.readValue(result, Transponders.class);

		assertEquals(1, readTrans.getId());
		assertThat(readTrans.getFrequency()).isNotEqualTo(0);
	}

	@Test
	public void shouldGetTransponders() throws JsonParseException, JsonMappingException, IOException {
		String result = transpondersJsonizer.getTransponders();
		checkTransListIsNotEmpty(result);
	}

	@Test
	public void shouldGetTranspondersBySatelliteId() throws JsonParseException, JsonMappingException, IOException {

		String result = transpondersJsonizer.getTranspondersBySatelliteId(3); // From
																				// id
																				// 3
																				// start
																				// imported
																				// transponders.
		checkTransListIsNotEmpty(result);
	}

	@Test
	public void shouldGetTranspondersByArbitraryFilter() throws JsonParseException, JsonMappingException, IOException {

		String result = transpondersJsonizer.getTranspondersByArbitraryFilter("Speed", "27500");

		checkTransListIsNotEmpty(result);
	}

	@Before
	public void setUp() {
		super.setUpAbstract();

		transpondersJsonizer = new TranspondersJsonizer();
		transpondersJsonizer.setObjectsController(objectsController);
		transpondersJsonizer.setCriterionService(criterionService);
		transpondersJsonizer.setListService(listService);

	}

	private TranspondersJsonizer transpondersJsonizer;

}

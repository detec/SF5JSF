package org.openbox.sf5.json.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.openbox.sf5.common.IniReader;
import org.openbox.sf5.common.TableFillerTests;
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
		// let's check the number of satellites.
		// List<Satellites> listSat = (List<Satellites>)
		// this.listService.ObjectsList(Satellites.class);
		// listSat.stream().forEach(t -> System.out.println("Sat id: " +
		// Long.toString(t.getId())));
		// System.out.println("Quantity of satellites: " +
		// Integer.toString(listSat.size()));

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

	@Test
	public void shouldImportTestInis() throws URISyntaxException {

		int positiveResult = 0;
		try {
			positiveResult = getIniImportResult();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(3, positiveResult);

	}

	public int getIniImportResult() throws IOException, URISyntaxException {

		IniReader iniReader = new IniReader();
		iniReader.setCm(cm);
		iniReader.setContr(contr);

		List<Boolean> resultList = new ArrayList<>();

		URL transpondersFolderUrl = Thread.currentThread().getContextClassLoader().getResource("transponders/");

		Path path = Paths.get(transpondersFolderUrl.toURI());

		// if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
		// System.out.println("Folder " + path + " does not exist!");
		// return 0;
		// }
		Stream<Path> streamPath = Files.find(path, 2, (newpath, attr) -> String.valueOf(newpath).endsWith(".ini"));

		// File[] matchingFiles = transpondersFolderFile.listFiles(new
		// FilenameFilter() {
		// @Override
		// public boolean accept(File dir, String name) {
		// return name.endsWith("ini");
		// }
		// });

		streamPath.forEach(t -> {
			iniReader.setFilepath(t.toString());
			try {
				iniReader.readData();
				resultList.add(iniReader.isResult());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		streamPath.close();

		int positiveResult = resultList.stream().filter(t -> t.booleanValue()).collect(Collectors.toList()).size();

		return positiveResult;
	}

	@Before
	public void setUp() {
		super.setUpAbstract();

		transpondersJsonizer = new TranspondersJsonizer();
		transpondersJsonizer.setContr(contr);
		transpondersJsonizer.setCriterionService(criterionService);
		transpondersJsonizer.setListService(listService);

		// we need 2 to setup catalogues before transponders import
		TableFillerTests tft = new TableFillerTests();
		tft.setUpAbstract(); // fill dependencies
		tft.executeTableFiller();
	}

	private TranspondersJsonizer transpondersJsonizer;

}

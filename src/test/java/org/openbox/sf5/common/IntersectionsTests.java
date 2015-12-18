package org.openbox.sf5.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.openbox.sf5.json.service.AbstractJsonizerTest;
import org.openbox.sf5.model.Settings;
import org.openbox.sf5.model.SettingsConversion;
import org.openbox.sf5.model.Transponders;
import org.openbox.sf5.model.Users;

public class IntersectionsTests extends AbstractJsonizerTest {

	@Before
	public void setUp() {
		super.setUpAbstract();

		// we need 2 to setup catalogues before transponders import
		TableFillerTests tft = new TableFillerTests();
		tft.setUpAbstract(); // fill dependencies
		tft.executeTableFiller();
	}

	@Test
	public void shouldImportTestInis() throws URISyntaxException {

		int positiveResult = 0;
		try {
			positiveResult = getIniImportResult();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// check that we have 3 positive *.ini file import.
		assertEquals(3, positiveResult);

	}

	public int getIniImportResult() throws IOException, URISyntaxException {

		IniReader iniReader = new IniReader();
		iniReader.setCm(cm);
		iniReader.setObjectsController(objectsController);

		List<Boolean> resultList = new ArrayList<>();

		URL transpondersFolderUrl = Thread.currentThread().getContextClassLoader().getResource("transponders/");

		Path path = Paths.get(transpondersFolderUrl.toURI());
		// Java NIO, looking for *.ini files
		Stream<Path> streamPath = Files.find(path, 2, (newpath, attr) -> String.valueOf(newpath).endsWith(".ini"));

		streamPath.forEach(t -> {
			iniReader.setFilepath(t.toString());
			try {
				iniReader.readData();
				resultList.add(iniReader.isResult());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		streamPath.close();

		int positiveResult = resultList.stream().filter(t -> t.booleanValue()).collect(Collectors.toList()).size();

		return positiveResult;
	}

	@Test
	public void shouldCheckIntersections() throws SQLException {
		Users usr = new Users();
		usr.setLogin("login");
		usr.setName("test user");
		objectsController.saveOrUpdate(usr);

		Settings setting = new Settings();
		setting.setName("Intersections test");
		setting.setUser(usr);
		setting.setTheLastEntry(new java.sql.Timestamp(System.currentTimeMillis()));
		objectsController.saveOrUpdate(setting);

		List<Transponders> transList = listService.ObjectsList(Transponders.class);

		List<SettingsConversion> scList = new ArrayList<SettingsConversion>();
		for (int i = 7; i < 39; i++) {
			// adding lines to setting
			SettingsConversion newLine = new SettingsConversion(setting);
			newLine.setLineNumber(i - 6);
			newLine.setTransponder(transList.get(i));
			scList.add(newLine);
		}

		Intersections intersections = new Intersections();
		intersections.setCm(cm);
		// if something is wrong - test will fail.
		int rows = intersections.checkIntersection(scList, setting);

	}

	public static Stream<Path> getTransponderFilesStreamPath() throws URISyntaxException, IOException {

		URL transpondersFolderUrl = Thread.currentThread().getContextClassLoader().getResource("transponders/");

		assertThat(transpondersFolderUrl).isNotNull();

		Path path = Paths.get(transpondersFolderUrl.toURI());

		Stream<Path> streamPath = Files.find(path, 2, (newpath, attr) -> String.valueOf(newpath).endsWith(".ini"));

		return streamPath;
	}

}

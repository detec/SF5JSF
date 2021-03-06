package org.openbox.sf5.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.criterion.Criterion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openbox.sf5.json.service.AbstractJsonizerTest;
import org.openbox.sf5.model.Sat;
import org.openbox.sf5.model.Settings;
import org.openbox.sf5.model.SettingsConversion;

@RunWith(JUnit4.class)
public class VerifyXMLExporterTests extends AbstractJsonizerTest {

	@Before
	public void setUp() {
		super.setUpAbstract();
	}

	@Test
	public void shouldVerifyXMLExport() throws IOException, JAXBException, URISyntaxException {
		// we previously saved this setting
		Criterion criterion = criterionService.getCriterionByClassFieldAndStringValue(Settings.class, "Name",
				"Intersections test");

		List<Settings> settList = listService.ObjectsCriterionList(Settings.class, criterion);

		assertEquals(1, settList.size());

		Settings setting = settList.get(0);

		List<SettingsConversion> conversionLines = setting.getConversion();
		assertThat(conversionLines.size()).isGreaterThan(0);

		Sat sat = XMLExporter.exportSettingsConversionPresentationToSF5Format(conversionLines);

		// http://www.concretepage.com/spring/spring-jaxb-integration-annotation-pretty-print-example-with-jaxb2marshaller
//		 try (FileOutputStream fos = new FileOutputStream("sf5output.xml");) {
//		 JAXB.marshal(sat, new StreamResult(fos));
//		 }

		StringWriter sw = new StringWriter();

		URL responseFile = ClassLoader.getSystemResource("xml/sf5output.xml");
		assertThat(responseFile).isNotNull();

		URI uri = responseFile.toURI();
		assertThat(uri).isNotNull();

		String content = new String(Files.readAllBytes(Paths.get(uri)), Charset.forName("UTF-8"));
		content = content.replace("\r\n\r\n", "\r\n"); // it adds
														// superfluous
														// \r\n

		// marshalling sat
		JAXB.marshal(sat, new StreamResult(sw));

		assertEquals(sw.toString(), content);

	}

}

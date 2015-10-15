package org.openbox.sf5.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openbox.sf5.application.SettingsFormController.SettingsConversionPresentation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLExporter {

	public static String exportSettingToXML(
			List<SettingsConversionPresentation> dataSettingsConversion) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		String absolutePath = "";
		try {

			// create a temp file
			File temp = File.createTempFile("transponders", ".xml");
			absolutePath = temp.getAbsolutePath();

		} catch (IOException e) {

			msgs.add(new FacesMessage("Error saving file on server \n"
					+ e.getLocalizedMessage()));
			return "";

		}

		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			Element mainRootElement = doc.createElement("sat");
			doc.appendChild(mainRootElement);

			long oldsatindex = 0;
			// long oldtpindex = 0;
			Element satId = null;

			for (SettingsConversionPresentation e : dataSettingsConversion) {
				long currentSatIndex = e.getSatindex();
				if (currentSatIndex != oldsatindex) {
					oldsatindex = currentSatIndex;
					satId = doc.createElement("satid");
					satId.setAttribute("index", String.valueOf(currentSatIndex));
					mainRootElement.appendChild(satId);
				}

				satId.appendChild(getTransponderNode(doc, e));

			}

			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					"yes");

			DOMSource source = new DOMSource(doc);
			StreamResult fileresult = new StreamResult(new File(absolutePath));
			transformer.transform(source, fileresult);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return absolutePath;

	}

	// utility method to create text node
	private static Node getTpElements(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

	private static Node getTransponderNode(Document doc,
			SettingsConversionPresentation e) {
		Element tpId = null;

		long currentTpIndex = e.getTpindex();
		tpId = doc.createElement("tp");
		tpId.setAttribute("index", String.valueOf(currentTpIndex));
		tpId.appendChild(getTpElements(doc, "LnbFreq",
				String.valueOf(e.getCarrier())));
		tpId.appendChild(getTpElements(doc, "Freq",
				String.valueOf(e.getTransponder().getFrequency())));
		tpId.appendChild(getTpElements(doc, "Symbol",
				String.valueOf(e.getSpeed())));
		tpId.appendChild(getTpElements(doc, "Polar",
				org.openbox.sf5.db.Polarization.getXMLpresentation(e
						.getPolarization())));
		return tpId;
	}

}

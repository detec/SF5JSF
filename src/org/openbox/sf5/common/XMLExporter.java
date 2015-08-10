package org.openbox.sf5.common;

import java.io.File;
import java.util.List;

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

	public static void exportSettingToXML(List<SettingsConversionPresentation> dataSettingsConversion
			, String filePath) {
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

        	Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult fileresult = new StreamResult(new File(filePath));
            transformer.transform(source, fileresult);

//    		Alert alert = new Alert(AlertType.INFORMATION);
//    		alert.setTitle("Information");
//    		alert.setHeaderText("XML export result");
//    		alert.setContentText("Settings conversion XML unload success!");
//    		alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }


	}

    // utility method to create text node
    private static Node getTpElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private static Node getTransponderNode(Document doc, SettingsConversionPresentation e) {
    	Element tpId = null;

    	long currentTpIndex = e.getTpindex();
    	tpId = doc.createElement("tp");
		tpId.setAttribute("index", String.valueOf(currentTpIndex));
		tpId.appendChild(getTpElements(doc, "LnbFreq", String.valueOf(e.getCarrier())));
		tpId.appendChild(getTpElements(doc, "Freq", String.valueOf(e.getTransponder().getFrequency())));
		tpId.appendChild(getTpElements(doc, "Symbol", String.valueOf(e.getSpeed())));
		tpId.appendChild(getTpElements(doc, "Polar", org.openbox.sf5.db.Polarization.getXMLpresentation(e.getPolarization())));
    	return tpId;
    }

}

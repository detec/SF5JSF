package org.openbox.sf5.application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.openbox.sf5.common.IniReader;

@Named("importFile")
@ViewScoped
public class ImportTranspondersFile implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6409041462672614971L;

	private Part file;
	private String fileContent;

	public void upload() {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();

		try {
			fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
		} catch (IOException e) {

			msgs.add(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error reading XML file!", e.getLocalizedMessage()));
			return;
		}

		try {

			// create a temp file
			File temp = File.createTempFile("transponders", ".xml");
			String absolutePath = temp.getAbsolutePath();
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absolutePath), "UTF-8"));

			try {
				out.write(fileContent);
			}

			finally {
				out.close();
			}

			// calling reader class
			IniReader getResult = new IniReader(absolutePath);
			if (getResult.isResult()) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "INI import result",
						"Import success!");
				FacesContext.getCurrentInstance().addMessage("messages", message);
			}

			else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "INI import result",
						"Import failure!");
				FacesContext.getCurrentInstance().addMessage("messages", message);
			}

		} catch (IOException e) {

			msgs.add(new FacesMessage("Error saving file on server \n" + e.getLocalizedMessage()));
			return;

		}

	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;

		// if (!"text/xml".equals(file.getContentType())) {
		// msgs.add(new FacesMessage("Not an xml file!"));
		// }
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

}

package org.openbox.sf5.application;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.service.ObjectsListService;

@Named("transpondersListController")
@ViewScoped
public class TranspondersListController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1000488396477162309L;

	public List<Transponders> getTranspondersList() {

		return (List<Transponders>) ObjectsListService
				.ObjectsList(Transponders.class);
	}

}

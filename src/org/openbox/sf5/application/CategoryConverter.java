package org.openbox.sf5.application;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import org.openbox.sf5.db.Transponders;
import org.openbox.sf5.service.ObjectsController;

@Named("transpondersConverter")
@FacesConverter(value = "transpondersConverter")
public class CategoryConverter implements Converter {

	// @PersistenceContext(unitName = "luavipuPU")
	// I include this because you will need to
	// lookup your entities based on submitted values
	// private transient EntityManager em;

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {
		// This will return the actual object representation
		// of your Category using the value (in your case 52)
		// returned from the client side
		// return em.find(Category.class, new BigInteger(value));
		ObjectsController contr = new ObjectsController();
		return contr.select(Transponders.class, Long.parseLong(value));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		// This will return view-friendly output for the dropdown menu
		// return ((Category) o).name();
		return ((Transponders) o).toString();

	}
}

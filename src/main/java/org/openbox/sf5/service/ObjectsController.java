package org.openbox.sf5.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ObjectsController implements Serializable {


	private static final long serialVersionUID = 3569160314988465165L;

	public <T> void add(T obj) {
		Service.add(obj);
	}

	public <T> void update(T obj) {
		Service.update(obj);
	}

	public <T> void remove(Class<T> type, long id) {
		Service.remove(type, id);
	}

	public <T> void saveOrUpdate(T obj) {
		Service.saveOrUpdate(obj);
	}

	public <T>  T select(Class<T> type, long id) {
		return Service.select(type, id);
	}

	@Inject
	private ObjectService Service;

	public ObjectService getService() {
		return Service;
	}

	public void setService(ObjectService service) {
		Service = service;
	}


}

package org.openbox.sf5.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ObjectsController implements Serializable {

	private static final long serialVersionUID = 3569160314988465165L;

	public void add(Object obj) {
		Service.add(obj);
	}

	public void update(Object obj) {
		Service.update(obj);
	}

	public void remove(Class<?> clazz, long id) {
		Service.remove(clazz, id);
	}

	public void saveOrUpdate(Object obj) {
		Service.saveOrUpdate(obj);
	}

	public Object select(Class<?> clazz, long id) {
		return Service.select(clazz, id);
	}

	@Inject
	private ObjectService Service;

}

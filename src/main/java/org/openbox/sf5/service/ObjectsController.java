package org.openbox.sf5.service;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.openbox.sf5.model.AbstractDbEntity;

@Named
@ApplicationScoped
public class ObjectsController implements Serializable {

	private static final long serialVersionUID = 3569160314988465165L;

	public <T extends AbstractDbEntity> void add(T obj) {
		Service.add(obj);
	}

	public <T extends AbstractDbEntity> void update(T obj) {
		Service.update(obj);
	}

	public <T extends AbstractDbEntity> void remove(Class<T> type, long id) {
		Service.remove(type, id);
	}

	public <T extends AbstractDbEntity> void saveOrUpdate(T obj) {
		Service.saveOrUpdate(obj);
	}

	public <T extends AbstractDbEntity> T select(Class<T> type, long id) {
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

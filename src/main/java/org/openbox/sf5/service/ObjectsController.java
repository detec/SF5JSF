package org.openbox.sf5.service;

public class ObjectsController {

	private static ObjectService Service = new ObjectServiceImpl();
	// private ObservableList<Transponders> List =
	// FXCollections.observableArrayList();

	public static void add(Object obj) {
		Service.add(obj);
	}

	public static void update(Object obj) {
		Service.update(obj);
	}

	public static void remove(Class<?> clazz, long id) {
		Service.remove(clazz, id);
	}

	public static void saveOrUpdate(Object obj) {
		Service.saveOrUpdate(obj);
	}

	public static Object select(Class<?> clazz, long id) {
		return Service.select(clazz, id);
	}

}

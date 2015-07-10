package org.openbox.sf5.service;

//import java.util.List;

public interface ObjectService {

	public void add(Object obj);
	
	//public List<Object> list(String className);
	
	public void remove(Class<?> clazz, long id);
	
	public void update(Object obj);
	
	public Object select(Class<?> clazz, long id);
	
	public void saveOrUpdate(Object obj);
}

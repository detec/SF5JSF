package org.openbox.sf5.model.listwrappers;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.openbox.sf5.model.Satellites;

// http://stackoverflow.com/questions/14268981/modify-a-class-definitions-annotation-string-parameter-at-runtime
// Changing annotation value at runtime

// http://stackoverflow.com/questions/14057932/javax-xml-bind-jaxbexception-class-nor-any-of-its-super-class-is-known-to-t
@XmlRootElement(name = "classstub")
@XmlSeeAlso(value = { Satellites.class })
public class GenericXMLListWrapper<T> {

	// http://blog.xebia.com/acessing-generic-types-at-runtime-in-java/
	@XmlTransient
	private Class entityBeanType;

	public GenericXMLListWrapper() {
		Class<?> cls = getClass();
		// while (!(cls.getSuperclass() == null
		// // || cls.getSuperclass().equals(AbstractExpression.class)
		//
		// )) {
		// cls = cls.getSuperclass();
		// }
		//
		// if (cls.getSuperclass() == null) {
		// throw new RuntimeException("Unexpected exception occurred.");
		// }

		this.entityBeanType = ((Class) ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@XmlTransient
	public Class getEntityBeanType() {
		return entityBeanType;
	}

	public void setEntityBeanType(Class entityBeanType) {
		this.entityBeanType = entityBeanType;
	}

	public void setWrappedList(List<T> wrappedList) {
		this.wrappedList = wrappedList;
	}

	private List<T> wrappedList;

	// http://stackoverflow.com/questions/16545868/exception-converting-object-to-xml-using-jaxb
	// @XmlElementWrapper(name = "stub")
	@XmlElement
	public List<T> getWrappedList() {
		if (wrappedList == null) {
			wrappedList = new ArrayList<>();
		}
		return wrappedList;
	}
}

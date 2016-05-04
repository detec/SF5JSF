package org.openbox.sf5.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

// This class should be used in Hibernate entities that are stored in database.
@MappedSuperclass
public abstract class AbstractDbEntity implements Serializable {

	private static final long serialVersionUID = 6240361962679174056L;

}

package org.openbox.sf5.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Satellites")
public class Satellites implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2077586473579019427L;

	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setSerialVersionUID(long ver) {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @SequenceGenerator(sequenceName = "catalog_seq")
	private long id;

	@Column(name = "Name", unique = false, nullable = false, length = 50)
	private String Name;

	@Override
	public String toString() {

		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getName() {
		return this.Name;
	}

	public long getId() {

		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Satellites(String Name) {

		this.Name = Name;

	}

	public Satellites() {

	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}

		if (!(other instanceof Satellites)) {
			return false;
		}
		Satellites otherSatellite = (Satellites) other;
		if (otherSatellite.Name.equals(Name)) {
			return true;
		} else {
			return false;
		}
	}

}
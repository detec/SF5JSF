package org.openbox.sf5.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Satellites")
@XmlRootElement
public class Satellites extends AbstractDbEntity implements Serializable {

	private static final long serialVersionUID = -2077586473579019427L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "Name", unique = false, nullable = false, length = 50)
	@JsonProperty("Name")
	private String Name;

	@Override
	public String toString() {

		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getName() {
		return Name;
	}

	public long getId() {

		return id;
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
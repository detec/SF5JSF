package org.openbox.sf5.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SettingsSatellites")
public class SettingsSatellites extends AbstractDbEntity implements Serializable {

	private static final long serialVersionUID = -2945693668519991789L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long id;

	public long getId() {

		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id", unique = false, nullable = false)
	private Settings parent_id;

	@GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
	@JsonProperty("LineNumber")
	private long LineNumber;

	public SettingsSatellites(Settings parent_id, long LineNumber) {

		this.parent_id = parent_id;
		this.LineNumber = LineNumber;

	}

	@ManyToOne
	@JoinColumn(name = "Satellite", unique = false, nullable = false)
	@JsonProperty("Satellite")
	private Satellites Satellite;

	public Satellites getSatellite() {
		return this.Satellite;
	}

	public void setSatellite(Satellites Satellite) {
		this.Satellite = Satellite;
	}

	public Settings getparent_id() {
		return this.parent_id;
	}

	public void setparent_id(Settings parent_id) {
		this.parent_id = parent_id;
	}

	public long getLineNumber() {
		return this.LineNumber;
	}

	public void setLineNumber(long LineNumber) {
		this.LineNumber = LineNumber;
	}

	public SettingsSatellites(Settings parent_id, long LineNumber, Satellites Satellite) {

		this.parent_id = parent_id;
		this.LineNumber = LineNumber;
		this.Satellite = Satellite;

	}

	public SettingsSatellites() {
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}

		if (!(other instanceof SettingsSatellites)) {
			return false;
		}
		SettingsSatellites otherSettingsSatellites = (SettingsSatellites) other;
		if (otherSettingsSatellites.parent_id.equals(this.parent_id)
				&& otherSettingsSatellites.Satellite.equals(this.Satellite)) {
			return true;
		} else {
			return false;
		}

	}

}
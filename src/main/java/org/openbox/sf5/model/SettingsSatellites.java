package org.openbox.sf5.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "SettingsSatellites")
@XmlRootElement(name = "SatellitesTable") // or we get Jaxb marshaller exception
											// duplicate names // This is
											// necessary for MOXy activation
											// http://lagod.id.au/blog/?p=472
@XmlAccessorType(XmlAccessType.FIELD)
public class SettingsSatellites extends AbstractDbEntity implements Serializable {

	private static final long serialVersionUID = -2945693668519991789L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long id;

	public long getId() {

		return id;
	}

	@JsonSetter
	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "parent_id", unique = false, nullable = false, foreignKey = @ForeignKey(name = "FK_Setting") )
	@JsonBackReference
	@XmlElement
	@XmlInverseReference(mappedBy = "Satellites")
	// @XmlTransient
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
	@JoinColumn(name = "Satellite", unique = false, nullable = false, foreignKey = @ForeignKey(name = "FK_Satellite") )
	@JsonProperty("Satellite")
	private Satellites Satellite;

	public Satellites getSatellite() {
		return Satellite;
	}

	public void setSatellite(Satellites Satellite) {
		this.Satellite = Satellite;
	}

	// @XmlTransient
	// @XmlElement
	// @XmlInverseReference(mappedBy="satellites")
	public Settings getparent_id() {
		return parent_id;
	}

	public void setparent_id(Settings parent_id) {
		this.parent_id = parent_id;
	}

	public long getLineNumber() {
		return LineNumber;
	}

	public void setLineNumber(long LineNumber) {
		this.LineNumber = LineNumber;
	}

	public SettingsSatellites(Settings parent_id, long LineNumber, Satellites Satellite) {

		this.parent_id = parent_id;
		this.LineNumber = LineNumber;
		this.Satellite = Satellite;

	}

	// No-args constructor

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
		if (otherSettingsSatellites.parent_id.equals(parent_id)
				&& otherSettingsSatellites.Satellite.equals(Satellite)) {
			return true;
		} else {
			return false;
		}

	}

}
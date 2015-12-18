package org.openbox.sf5.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Transponders")
public class Transponders extends AbstractDbEntity implements Serializable {

	private static final long serialVersionUID = -3945460836260580586L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public long getId() {

		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "Frequency", unique = false, nullable = false, precision = 5)
	@Min(value = 2000)
	@JsonProperty("Frequency")
	private long Frequency;

	public long getFrequency() {
		return Frequency;
	}

	public void setFrequency(long Frequency) {
		this.Frequency = Frequency;
	}

	@Override
	public String toString() {
		return String.valueOf(Frequency);
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	@NotNull
	@JsonProperty("Polarization")
	private Polarization Polarization;

	public Polarization getPolarization() {
		return Polarization;
	}

	public void setPolarization(Polarization Polarization) {
		this.Polarization = Polarization;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	@JsonProperty("FEC")
	@NotNull
	private TypesOfFEC FEC;

	public TypesOfFEC getFEC() {
		return FEC;
	}

	public void setFEC(TypesOfFEC FEC) {
		this.FEC = FEC;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	@NotNull
	@JsonProperty("Carrier")
	private CarrierFrequency Carrier;

	public CarrierFrequency getCarrier() {
		return Carrier;
	}

	public void setCarrier(CarrierFrequency Carrier) {
		this.Carrier = Carrier;
	}

	@Column(name = "Speed", unique = false, nullable = false, precision = 5)
	@Min(value = 1000)
	@JsonProperty("Speed")
	private long Speed;

	public long getSpeed() {
		return Speed;
	}

	public void setSpeed(long Speed) {
		this.Speed = Speed;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	@NotNull
	@JsonProperty("VersionOfTheDVB")
	private DVBStandards VersionOfTheDVB;

	public DVBStandards getVersionOfTheDVB() {
		return VersionOfTheDVB;
	}

	public void setVersionOfTheDVB(DVBStandards VersionOfTheDVB) {
		this.VersionOfTheDVB = VersionOfTheDVB;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	@NotNull
	@JsonProperty("RangeOfDVB")
	private RangesOfDVB RangeOfDVB;

	public RangesOfDVB getRangeOfDVB() {
		return RangeOfDVB;
	}

	public void setRangeOfDVB(RangesOfDVB RangeOfDVB) {
		this.RangeOfDVB = RangeOfDVB;
	}

	@ManyToOne
	@JoinColumn(name = "Satellite", unique = false, nullable = false)
	@NotNull
	@JsonProperty("Satellite")
	private Satellites Satellite;

	public Satellites getSatellite() {
		return Satellite;
	}

	public void setSatellite(Satellites Satellite) {
		this.Satellite = Satellite;
	}

	public Transponders(long Frequency, Polarization Polarization, TypesOfFEC FEC, CarrierFrequency Carrier, long Speed,
			DVBStandards VersionOfTheDVB, RangesOfDVB RangeOfDVB, Satellites Satellite) {

		this.Frequency = Frequency;
		this.Polarization = Polarization;
		this.FEC = FEC;
		this.Carrier = Carrier;
		this.Speed = Speed;
		this.VersionOfTheDVB = VersionOfTheDVB;
		this.RangeOfDVB = RangeOfDVB;
		this.Satellite = Satellite;

	}

	public Transponders() {
	}

	public Transponders(String pFrequency) {
		this.Frequency = Long.parseLong(pFrequency);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}

		if (!(other instanceof Transponders)) {
			return false;
		}
		Transponders otherTransponders = (Transponders) other;
		if (otherTransponders.Frequency == Frequency && otherTransponders.Polarization.equals(Polarization)
				&& otherTransponders.FEC.equals(FEC) && otherTransponders.Carrier.equals(Carrier)
				&& otherTransponders.Speed == Speed && otherTransponders.VersionOfTheDVB.equals(VersionOfTheDVB)
				&& otherTransponders.RangeOfDVB.equals(RangeOfDVB) && otherTransponders.Satellite.equals(Satellite)) {
			return true;
		} else {
			return false;
		}

	}

	protected void setObjectFieldsFrom(Transponders origObj) throws IllegalAccessException {
		Field fields[];
		Class curClass = origObj.getClass();

		if (!curClass.isAssignableFrom(this.getClass())) {
			throw new IllegalArgumentException("New object must be the same class or a subclass of original");
		}

		// Spin through all fields of the class & all its superclasses
		do {
			fields = curClass.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				if (fields[i].getName().equals("serialVersionUID")) {
					continue;
				}
				fields[i].set(this, fields[i].get(origObj));
			}
			curClass = curClass.getSuperclass();
		} while (curClass != null);
	}

	public Transponders(Transponders origObj) {
		try {
			setObjectFieldsFrom(origObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
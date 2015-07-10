package org.openbox.sf5.db;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.NaturalId;



@Entity
@Table(name="Transponders")
public class Transponders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3945460836260580586L;

	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "my_entity_seq_gen")
	@SequenceGenerator(name = "my_entity_seq_gen", sequenceName = "catalog_seq")
	private long id;

	@Column(name="Name", unique = false, nullable = true, length = 25)
		private String Name;

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

	@Column(name="Frequency", unique = false, nullable = false, precision = 5)
	private long Frequency;

	public long getFrequency() {
		return this.Frequency;
	}

 	public void setFrequency(long Frequency) {
		this.Frequency = Frequency;
	}


	
	@Override
	public String toString() {
		return String.valueOf(this.Frequency);
	}	

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Polarization Polarization;

	public Polarization getPolarization() {
		return this.Polarization;
	}

 	public void setPolarization(Polarization Polarization) {
		this.Polarization = Polarization;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private TypesOfFEC FEC;

	public TypesOfFEC getFEC() {
		return this.FEC;
	}

 	public void setFEC(TypesOfFEC FEC) {
		this.FEC = FEC;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private CarrierFrequency Carrier;

	public CarrierFrequency getCarrier() {
		return this.Carrier;
	}

 	public void setCarrier(CarrierFrequency Carrier) {
		this.Carrier = Carrier;
	}

	@Column(name="Speed", unique = false, nullable = true, precision = 5)
	private long Speed;

	public long getSpeed() {
		return this.Speed;
	}

 	public void setSpeed(long Speed) {
		this.Speed = Speed;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private DVBStandards VersionOfTheDVB;

	public DVBStandards getVersionOfTheDVB() {
		return this.VersionOfTheDVB;
	}

 	public void setVersionOfTheDVB(DVBStandards VersionOfTheDVB) {
		this.VersionOfTheDVB = VersionOfTheDVB;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true)
	private RangesOfDVB RangeOfDVB;

	public RangesOfDVB getRangeOfDVB() {
		return this.RangeOfDVB;
	}

 	public void setRangeOfDVB(RangesOfDVB RangeOfDVB) {
		this.RangeOfDVB = RangeOfDVB;
	}

	@ManyToOne
	@JoinColumn(name="Satellite", unique = false, nullable = false)
	private Satellites Satellite;

	public Satellites getSatellite() {
		return this.Satellite;
	}

 	public void setSatellite(Satellites Satellite) {
		this.Satellite = Satellite;
	}

	public Transponders(String Name, long Frequency, Polarization Polarization, TypesOfFEC FEC, CarrierFrequency Carrier, long Speed, DVBStandards VersionOfTheDVB, RangesOfDVB RangeOfDVB, Satellites Satellite) {

	this.Name= Name;
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

	@Override
	public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    
    if (!(other instanceof Transponders)) return false;
    Transponders otherTransponders = (Transponders) other;
		if (otherTransponders.Name.equals(this.Name)

&& otherTransponders.Frequency == this.Frequency
	&& otherTransponders.Polarization.equals(this.Polarization)
	&& otherTransponders.FEC.equals(this.FEC)
	&& otherTransponders.Carrier.equals(this.Carrier)
	&& otherTransponders.Speed == this.Speed
	&& otherTransponders.VersionOfTheDVB.equals(this.VersionOfTheDVB)
	&& otherTransponders.RangeOfDVB.equals(this.RangeOfDVB)
	&& otherTransponders.Satellite.equals(this.Satellite)
) return true;
	else return false;
    
}
}
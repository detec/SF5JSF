package org.openbox.sf5.db;

import java.io.Serializable;
import java.util.Objects;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.Session;

@Entity
@Table(name="ValueOfTheCarrierFrequency")
public class ValueOfTheCarrierFrequency implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6095308495476745108L;


	@Id
	CarrierFrequency TypeOfCarrierFrequency;

	public CarrierFrequency getTypeOfCarrierFrequency() {
		return this.TypeOfCarrierFrequency;
	}

	public void setTypeOfCarrierFrequency(CarrierFrequency TypeOfCarrierFrequency) {
		this.TypeOfCarrierFrequency = TypeOfCarrierFrequency;
	}
	
	@Id
	KindsOfPolarization Polarization;

	@Column(name="LowerThreshold", unique = false, nullable = false, precision = 5)
	private long LowerThreshold;

	public long getLowerThreshold() {
		return this.LowerThreshold;
	}

 	public void setLowerThreshold(long LowerThreshold) {
		this.LowerThreshold = LowerThreshold;
	}

	@Column(name="UpperThreshold", unique = false, nullable = false, precision = 5)
	private long UpperThreshold;

	public long getUpperThreshold() {
		return this.UpperThreshold;
	}

 	public void setUpperThreshold(long UpperThreshold) {
		this.UpperThreshold = UpperThreshold;
	}

	public ValueOfTheCarrierFrequency(CarrierFrequency TypeOfCarrierFrequency, KindsOfPolarization Polarization, long LowerThreshold, long UpperThreshold) {

		this.TypeOfCarrierFrequency = TypeOfCarrierFrequency;
		this.Polarization = Polarization;
		this.LowerThreshold = LowerThreshold;
		this.UpperThreshold = UpperThreshold;

	}

	public ValueOfTheCarrierFrequency() {
	}
}
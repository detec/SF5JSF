package org.openbox.sf5.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ValueOfTheCarrierFrequency")
public class ValueOfTheCarrierFrequency extends AbstractDbEntity implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Polarization == null) ? 0 : Polarization.hashCode());
		result = prime * result + ((TypeOfCarrierFrequency == null) ? 0 : TypeOfCarrierFrequency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ValueOfTheCarrierFrequency other = (ValueOfTheCarrierFrequency) obj;
		if (Polarization != other.Polarization) {
			return false;
		}
		if (TypeOfCarrierFrequency != other.TypeOfCarrierFrequency) {
			return false;
		}
		return true;
	}

	private static final long serialVersionUID = -6095308495476745108L;

	@Id
	CarrierFrequency TypeOfCarrierFrequency;

	public CarrierFrequency getTypeOfCarrierFrequency() {
		return TypeOfCarrierFrequency;
	}

	public void setTypeOfCarrierFrequency(CarrierFrequency TypeOfCarrierFrequency) {
		this.TypeOfCarrierFrequency = TypeOfCarrierFrequency;
	}

	@Id
	KindsOfPolarization Polarization;

	@Column(name = "LowerThreshold", unique = false, nullable = false, precision = 5)
	private long LowerThreshold;

	public long getLowerThreshold() {
		return LowerThreshold;
	}

	public void setLowerThreshold(long LowerThreshold) {
		this.LowerThreshold = LowerThreshold;
	}

	@Column(name = "UpperThreshold", unique = false, nullable = false, precision = 5)
	private long UpperThreshold;

	public long getUpperThreshold() {
		return UpperThreshold;
	}

	public void setUpperThreshold(long UpperThreshold) {
		this.UpperThreshold = UpperThreshold;
	}

	public ValueOfTheCarrierFrequency(CarrierFrequency TypeOfCarrierFrequency, KindsOfPolarization Polarization,
			long LowerThreshold, long UpperThreshold) {

		this.TypeOfCarrierFrequency = TypeOfCarrierFrequency;
		this.Polarization = Polarization;
		this.LowerThreshold = LowerThreshold;
		this.UpperThreshold = UpperThreshold;

	}

	public ValueOfTheCarrierFrequency() {
	}
}
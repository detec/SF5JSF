package org.openbox.sf5.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TheDVBRangeValues")
public class TheDVBRangeValues extends AbstractDbEntity implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((RangeOfDVB == null) ? 0 : RangeOfDVB.hashCode());
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
		TheDVBRangeValues other = (TheDVBRangeValues) obj;
		if (RangeOfDVB != other.RangeOfDVB) {
			return false;
		}
		return true;
	}

	private static final long serialVersionUID = 1635144675404567877L;

	@Id
	RangesOfDVB RangeOfDVB;

	public RangesOfDVB getRangeOfDVB() {
		return this.RangeOfDVB;
	}

	public void setRangeOfDVB(RangesOfDVB RangeOfDVB) {
		this.RangeOfDVB = RangeOfDVB;
	}

	@Column(name = "LowerThreshold", unique = false, nullable = true, precision = 5)
	private long LowerThreshold;

	public long getLowerThreshold() {
		return this.LowerThreshold;
	}

	public void setLowerThreshold(long LowerThreshold) {
		this.LowerThreshold = LowerThreshold;
	}

	@Column(name = "UpperThreshold", unique = false, nullable = true, precision = 5)
	private long UpperThreshold;

	public long getUpperThreshold() {
		return this.UpperThreshold;
	}

	public void setUpperThreshold(long UpperThreshold) {
		this.UpperThreshold = UpperThreshold;
	}

	public TheDVBRangeValues(RangesOfDVB RangeOfDVB, long LowerThreshold, long UpperThreshold) {

		this.RangeOfDVB = RangeOfDVB;
		this.LowerThreshold = LowerThreshold;
		this.UpperThreshold = UpperThreshold;

	}

	public TheDVBRangeValues() {
	}
}
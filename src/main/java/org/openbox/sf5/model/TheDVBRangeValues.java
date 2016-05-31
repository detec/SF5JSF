package org.openbox.sf5.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TheDVBRangeValues")
public class TheDVBRangeValues extends AbstractDbEntity implements Serializable {

	private static final long serialVersionUID = 1635144675404567877L;

	@Id
	private RangesOfDVB RangeOfDVB;

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
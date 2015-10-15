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
@Table(name="TheDVBRangeValues")
public class TheDVBRangeValues implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1635144675404567877L;

	@Id
	RangesOfDVB RangeOfDVB;
	
	public RangesOfDVB getRangeOfDVB() {
		return this.RangeOfDVB;
	}
	
	public void setRangeOfDVB(RangesOfDVB RangeOfDVB) {
		this.RangeOfDVB = RangeOfDVB;
	}
	
	@Column(name="LowerThreshold", unique = false, nullable = true, precision = 5)
	private long LowerThreshold;

	public long getLowerThreshold() {
		return this.LowerThreshold;
	}

 	public void setLowerThreshold(long LowerThreshold) {
		this.LowerThreshold = LowerThreshold;
	}

	@Column(name="UpperThreshold", unique = false, nullable = true, precision = 5)
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
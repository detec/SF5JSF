package org.openbox.sf5.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SettingsConversion")
@XmlRootElement // This is necessary for MOXy activation
				// http://lagod.id.au/blog/?p=472
@XmlAccessorType(XmlAccessType.FIELD)
public class SettingsConversion extends AbstractDbEntity implements Serializable {

	private static final long serialVersionUID = -399944579251735871L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public long getId() {

		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// https://jaxb.java.net/guide/Mapping_cyclic_references_to_XML.html

	// http://blog.cronn.de/cyclic-references-in-jaxb/

	@ManyToOne
	@JoinColumn(name = "parent_id", unique = false, nullable = false, foreignKey = @ForeignKey(name = "FK_Setting") )
	@JsonBackReference
	// @NotNull - probably this causes 400 error.
	@XmlElement
	@XmlInverseReference(mappedBy = "Conversion")
	// @XmlTransient
	@XmlIDREF
	private Settings parent_id;

	@JsonProperty("LineNumber")
	private long LineNumber;

	public SettingsConversion(Settings parent_id) {

		this.parent_id = parent_id;

	}

	@ManyToOne
	@JoinColumn(name = "Transponder", unique = false, nullable = false, foreignKey = @ForeignKey(name = "FK_Transponder") )
	@JsonProperty("Transponder")
	private Transponders Transponder;

	public Transponders getTransponder() {
		return Transponder;
	}

	public void setTransponder(Transponders Transponder) {
		this.Transponder = Transponder;
	}

	@Column(name = "Satindex", unique = false, nullable = true, precision = 1)
	@JsonProperty("Satindex")
	private long Satindex;

	public long getSatindex() {
		return Satindex;
	}

	public void setSatindex(long Satindex) {
		this.Satindex = Satindex;
	}

	@Column(name = "Tpindex", unique = false, nullable = true, precision = 1)
	@JsonProperty("Tpindex")
	private long Tpindex;

	public long getTpindex() {
		return Tpindex;
	}

	public void setTpindex(long Tpindex) {
		this.Tpindex = Tpindex;
	}

	@Column(name = "Note", unique = false, nullable = true, length = 120)
	@JsonProperty("Note")
	private String Note;

	public String getNote() {
		return Note;
	}

	public void setNote(String Note) {
		this.Note = Note;
	}

	@Column(name = "TheLineOfIntersection", unique = false, nullable = true, precision = 2)
	@JsonProperty("TheLineOfIntersection")
	private long TheLineOfIntersection;

	public long getTheLineOfIntersection() {
		return TheLineOfIntersection;
	}

	public void setTheLineOfIntersection(long TheLineOfIntersection) {
		this.TheLineOfIntersection = TheLineOfIntersection;
	}

	// @XmlTransient
	// @XmlInverseReference(mappedBy="Conversion")
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

	public SettingsConversion(Settings parent_id, Transponders Transponder, long Satindex, long Tpindex, String Note,
			long TheLineOfIntersection) {

		this.parent_id = parent_id;
		this.Transponder = Transponder;
		this.Satindex = Satindex;
		this.Tpindex = Tpindex;
		this.Note = Note;
		this.TheLineOfIntersection = TheLineOfIntersection;

	}

	// No-args constructor
	public SettingsConversion() {
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other == this) {
			return true;
		}

		if (!(other instanceof SettingsConversion)) {
			return false;
		}
		SettingsConversion otherSettingsConversion = (SettingsConversion) other;
		if (otherSettingsConversion.parent_id.equals(parent_id)
				&& otherSettingsConversion.Transponder.equals(Transponder)
				&& otherSettingsConversion.Satindex == Satindex && otherSettingsConversion.Tpindex == Tpindex
				&& otherSettingsConversion.Note.equals(Note)
				&& otherSettingsConversion.TheLineOfIntersection == TheLineOfIntersection) {
			return true;
		} else {
			return false;
		}

	}

	protected void setObjectFieldsFrom(SettingsConversion origObj) throws IllegalAccessException {
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

	public SettingsConversion(SettingsConversion origObj) {
		try {
			setObjectFieldsFrom(origObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
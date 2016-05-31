package org.openbox.sf5.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.openbox.sf5.json.converters.TimestampAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

// https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations
// http://www.ibm.com/developerworks/rational/library/resolve-jaxb-cycle-errors/index.html
// Mapping JPA entities to successfully transcode them using XML

@Entity
// For some weird reason says: database column 'user' not found
// Container and unit tests fail.
// @Table(name = "Settings", indexes = { @Index(columnList = "\"user\"", name =
// "\"user\"") })
@Table(name = "Settings")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Settings extends AbstractDbEntity implements Serializable {

	private static final long serialVersionUID = 7055744176770843683L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "Name", unique = false, nullable = false, length = 50)
	@NotEmpty
	@JsonProperty("Name")
	@XmlID
	private String Name;

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getName() {
		return Name;
	}

	public long getId() {

		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return Name;
	}

	@Column(name = "PropsFile", unique = false, nullable = true, length = 300)
	@JsonProperty("PropsFile")
	private String PropsFile;

	public String getPropsFile() {
		return PropsFile;
	}

	public void setPropsFile(String PropsFile) {
		this.PropsFile = PropsFile;
	}

	@Column(name = "TheLastEntry", unique = false, nullable = true)
	@JsonProperty("TheLastEntry")
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =
	// "yyyy-MM-dd'T'HH:mm:ssZ")
	// shape=JsonFormat.Shape - it is new for Jackson
	// it is only for Jackson
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "Europe/Kiev")
	@NotNull
	// Commented this Timestamp adapter as it ddin't want to convert it to
	// non-empty timestamp
	@XmlJavaTypeAdapter(TimestampAdapter.class) // this is for MOXy
												// http://www.eclipse.org/eclipselink/documentation/2.6/moxy/advanced_concepts006.htm
	private Timestamp TheLastEntry; // As JSON with MOXy it comes as expected
									// with TimestampAdapter dateformat from
									// server

	public Timestamp getTheLastEntry() {
		return TheLastEntry;
	}

	public void setTheLastEntry(Timestamp TheLastEntry) {
		this.TheLastEntry = TheLastEntry;
	}

	// http://stackoverflow.com/questions/4350874/unable-to-use-table-named-user-in-postgresql-hibernate
	@ManyToOne
	@JoinColumn(name = "\"user\"", unique = false, nullable = false, foreignKey = @ForeignKey(name = "FK_User"))
	@NotNull
	@JsonProperty("User")
	@Valid
	private Users User;

	public Users getUser() {
		return User;
	}

	public void setUser(Users User) {
		this.User = User;
	}

	@OneToMany(mappedBy = "parent_id", fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	@OrderColumn(name = "LineNumber")
	@JsonProperty("Conversion")
	@JsonManagedReference
	private List<SettingsConversion> Conversion;

	public List<SettingsConversion> getConversion() {
		return Conversion;
	}

	public void setConversion(List<SettingsConversion> Conversion) {
		this.Conversion = Conversion;
	}

	@OneToMany(mappedBy = "parent_id", fetch = FetchType.EAGER, orphanRemoval = true)
	@Cascade({ CascadeType.SAVE_UPDATE, CascadeType.DELETE })
	@OrderColumn(name = "LineNumber")
	@JsonProperty("Satellites")
	@JsonManagedReference
	private List<SettingsSatellites> Satellites;

	public List<SettingsSatellites> getSatellites() {
		return Satellites;
	}

	public void setSatellites(List<SettingsSatellites> Satellites) {
		this.Satellites = Satellites;
	}

	public Settings(String Name, String PropsFile, Timestamp lastEntry, Users User, List<SettingsConversion> Conversion,
			List<SettingsSatellites> Satellites) {

		this.Name = Name;
		this.PropsFile = PropsFile;
		TheLastEntry = lastEntry;
		this.User = User;
		this.Conversion = Conversion;
		this.Satellites = Satellites;

	}

	// No-args constructor

	public Settings() {
	}

	public Settings(String pName) {
		Name = pName;
	}

}
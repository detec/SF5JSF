
package org.openbox.sf5.wsmodel;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for settings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="settings">
 *   &lt;complexContent>
 *     &lt;extension base="{http://wsmodel.sf5.openbox.org/}abstractDbEntity">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}ID" minOccurs="0"/>
 *         &lt;element name="PropsFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TheLastEntry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="User" type="{http://wsmodel.sf5.openbox.org/}users" minOccurs="0"/>
 *         &lt;element name="Conversion" type="{http://wsmodel.sf5.openbox.org/}settingsConversion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Satellites" type="{http://wsmodel.sf5.openbox.org/}settingsSatellites" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "settings", propOrder = {
    "id",
    "name",
    "propsFile",
    "theLastEntry",
    "user",
    "conversion",
    "satellites"
})
public class Settings
    extends AbstractDbEntity
{

    protected long id;
    @XmlElement(name = "Name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String name;
    @XmlElement(name = "PropsFile")
    protected String propsFile;
    @XmlElement(name = "TheLastEntry")
    protected String theLastEntry;
    @XmlElement(name = "User")
    protected Users user;
    @XmlElement(name = "Conversion", nillable = true)
    protected List<SettingsConversion> conversion;
    @XmlElement(name = "Satellites", nillable = true)
    protected List<SettingsSatellites> satellites;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the propsFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropsFile() {
        return propsFile;
    }

    /**
     * Sets the value of the propsFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropsFile(String value) {
        this.propsFile = value;
    }

    /**
     * Gets the value of the theLastEntry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTheLastEntry() {
        return theLastEntry;
    }

    /**
     * Sets the value of the theLastEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTheLastEntry(String value) {
        this.theLastEntry = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link Users }
     *     
     */
    public Users getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link Users }
     *     
     */
    public void setUser(Users value) {
        this.user = value;
    }

    /**
     * Gets the value of the conversion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conversion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConversion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SettingsConversion }
     * 
     * 
     */
    public List<SettingsConversion> getConversion() {
        if (conversion == null) {
            conversion = new ArrayList<SettingsConversion>();
        }
        return this.conversion;
    }

    /**
     * Gets the value of the satellites property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the satellites property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSatellites().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SettingsSatellites }
     * 
     * 
     */
    public List<SettingsSatellites> getSatellites() {
        if (satellites == null) {
            satellites = new ArrayList<SettingsSatellites>();
        }
        return this.satellites;
    }

}

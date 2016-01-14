
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for settingsSatellites complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="settingsSatellites">
 *   &lt;complexContent>
 *     &lt;extension base="{http://wsmodel.sf5.openbox.org/}abstractDbEntity">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="parent_id" type="{http://wsmodel.sf5.openbox.org/}settings" minOccurs="0"/>
 *         &lt;element name="LineNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Satellite" type="{http://wsmodel.sf5.openbox.org/}satellites" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "settingsSatellites", propOrder = {
    "id",
    "parentId",
    "lineNumber",
    "satellite"
})
public class SettingsSatellites
    extends AbstractDbEntity
{

    protected long id;
    @XmlElement(name = "parent_id")
    protected Settings parentId;
    @XmlElement(name = "LineNumber")
    protected long lineNumber;
    @XmlElement(name = "Satellite")
    protected Satellites satellite;

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
     * Gets the value of the parentId property.
     * 
     * @return
     *     possible object is
     *     {@link Settings }
     *     
     */
    public Settings getParentId() {
        return parentId;
    }

    /**
     * Sets the value of the parentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Settings }
     *     
     */
    public void setParentId(Settings value) {
        this.parentId = value;
    }

    /**
     * Gets the value of the lineNumber property.
     * 
     */
    public long getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets the value of the lineNumber property.
     * 
     */
    public void setLineNumber(long value) {
        this.lineNumber = value;
    }

    /**
     * Gets the value of the satellite property.
     * 
     * @return
     *     possible object is
     *     {@link Satellites }
     *     
     */
    public Satellites getSatellite() {
        return satellite;
    }

    /**
     * Sets the value of the satellite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Satellites }
     *     
     */
    public void setSatellite(Satellites value) {
        this.satellite = value;
    }

}

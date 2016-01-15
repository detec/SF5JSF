
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transponders complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transponders">
 *   &lt;complexContent>
 *     &lt;extension base="{http://wsmodel.sf5.openbox.org/}abstractDbEntity">
 *       &lt;sequence>
 *         &lt;element name="carrier" type="{http://wsmodel.sf5.openbox.org/}carrierFrequency" minOccurs="0"/>
 *         &lt;element name="FEC" type="{http://wsmodel.sf5.openbox.org/}typesOfFEC" minOccurs="0"/>
 *         &lt;element name="frequency" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="polarization" type="{http://wsmodel.sf5.openbox.org/}polarization" minOccurs="0"/>
 *         &lt;element name="rangeOfDVB" type="{http://wsmodel.sf5.openbox.org/}rangesOfDVB" minOccurs="0"/>
 *         &lt;element name="satellite" type="{http://wsmodel.sf5.openbox.org/}satellites" minOccurs="0"/>
 *         &lt;element name="speed" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="versionOfTheDVB" type="{http://wsmodel.sf5.openbox.org/}dvbStandards" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transponders", propOrder = {
    "carrier",
    "fec",
    "frequency",
    "id",
    "polarization",
    "rangeOfDVB",
    "satellite",
    "speed",
    "versionOfTheDVB"
})
public class Transponders
    extends AbstractDbEntity
{

    @XmlSchemaType(name = "string")
    protected CarrierFrequency carrier;
    @XmlElement(name = "FEC")
    protected String fec;
    protected long frequency;
    protected long id;
    @XmlSchemaType(name = "string")
    protected Polarization polarization;
    @XmlSchemaType(name = "string")
    protected RangesOfDVB rangeOfDVB;
    protected Satellites satellite;
    protected long speed;
    @XmlSchemaType(name = "string")
    protected DvbStandards versionOfTheDVB;

    /**
     * Gets the value of the carrier property.
     * 
     * @return
     *     possible object is
     *     {@link CarrierFrequency }
     *     
     */
    public CarrierFrequency getCarrier() {
        return carrier;
    }

    /**
     * Sets the value of the carrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link CarrierFrequency }
     *     
     */
    public void setCarrier(CarrierFrequency value) {
        this.carrier = value;
    }

    /**
     * Gets the value of the fec property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFEC() {
        return fec;
    }

    /**
     * Sets the value of the fec property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFEC(String value) {
        this.fec = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     */
    public long getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     */
    public void setFrequency(long value) {
        this.frequency = value;
    }

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
     * Gets the value of the polarization property.
     * 
     * @return
     *     possible object is
     *     {@link Polarization }
     *     
     */
    public Polarization getPolarization() {
        return polarization;
    }

    /**
     * Sets the value of the polarization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Polarization }
     *     
     */
    public void setPolarization(Polarization value) {
        this.polarization = value;
    }

    /**
     * Gets the value of the rangeOfDVB property.
     * 
     * @return
     *     possible object is
     *     {@link RangesOfDVB }
     *     
     */
    public RangesOfDVB getRangeOfDVB() {
        return rangeOfDVB;
    }

    /**
     * Sets the value of the rangeOfDVB property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangesOfDVB }
     *     
     */
    public void setRangeOfDVB(RangesOfDVB value) {
        this.rangeOfDVB = value;
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

    /**
     * Gets the value of the speed property.
     * 
     */
    public long getSpeed() {
        return speed;
    }

    /**
     * Sets the value of the speed property.
     * 
     */
    public void setSpeed(long value) {
        this.speed = value;
    }

    /**
     * Gets the value of the versionOfTheDVB property.
     * 
     * @return
     *     possible object is
     *     {@link DvbStandards }
     *     
     */
    public DvbStandards getVersionOfTheDVB() {
        return versionOfTheDVB;
    }

    /**
     * Sets the value of the versionOfTheDVB property.
     * 
     * @param value
     *     allowed object is
     *     {@link DvbStandards }
     *     
     */
    public void setVersionOfTheDVB(DvbStandards value) {
        this.versionOfTheDVB = value;
    }

}
